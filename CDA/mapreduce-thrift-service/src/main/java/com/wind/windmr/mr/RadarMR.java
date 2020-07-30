package com.wind.windmr.mr;

import com.alibaba.fastjson.JSON;
import com.wind.user.bean.OutDataBean;
import com.wind.windmr.domain.po.MRBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;

import java.io.File;
import java.io.IOException;

public class RadarMR {
    static {
        // 设置宿主机环境变量 HADOOP_USER_NAME == icss
        System.setProperty("HADOOP_USER_NAME", "icss");
        String osInfo = System.getProperty("os.name");
        if (osInfo.toLowerCase().indexOf("windows") != -1) {
            System.setProperty("hadoop.home.dir", "D:\\Program Files\\Hadoop");
            System.setProperty("hadooop.tmp.dir", "D:\\Program Files\\Hadoop\\tmp");

        }
    }

    // 1. 完成 Mapper
    public static class RadarMapper
            extends Mapper<LongWritable, Text, Text, MRBean> {
        private Text nameText = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            MRBean mrBean = new MRBean();

            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            String dataSourceFilename = inputSplit.getPath().getName();
            if (dataSourceFilename.indexOf("population") != -1) {
                // 是 人口数 文件
                String[] filed = line.split("::");
                nameText.set(filed[0]);
                mrBean.setPopulation(Integer.valueOf(filed[1]));
                mrBean.setFlag("population");
            } else {
                // 是 疫情信息 文件
                OutDataBean outDataBean = (OutDataBean) JSON.parse(line);
                String blockName = outDataBean.getCode() == "" ? outDataBean.getName() : outDataBean.getCode();
                nameText.set(blockName);
                mrBean.setConfirmed(outDataBean.getConfirmed());
                mrBean.setSuspected(outDataBean.getSuspected());
                mrBean.setDead(outDataBean.getDead());
                mrBean.setCured(outDataBean.getCured());
                mrBean.setFlag("database");
            }
            context.write(nameText, mrBean);
        }
    }

    // 2. 完成 reduce
    private static class RadarReducer
            extends Reducer<Text, MRBean, Text, MRBean> {
        @Override
        protected void reduce(Text nameText, Iterable<MRBean> mrBeans, Context context) throws IOException, InterruptedException {

            MRBean outMRBean = new MRBean();
            for (MRBean mrBean : mrBeans) {
                if (mrBean.getFlag() == "population") {
                    // 人口
                    outMRBean.setPopulation(mrBean.getPopulation());
                } else {
                    // 数据库
                    /*
                    private List<Integer> confirmed;
                    private List<Integer> cured;
                    private List<Integer> dead;
                    private List<Integer> suspected;
                    */
                    outMRBean.setConfirmed(mrBean.getConfirmed());
                    outMRBean.setCured(mrBean.getCured());
                    outMRBean.setDead(mrBean.getDead());
                    outMRBean.setSuspected(mrBean.getSuspected());
                }
            }
            context.write(nameText,outMRBean);
        }
    }
    // 3. MR2-Controller
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 0. 初始化 MR Job
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "Movie Lens Version 1.0");
        job.setJarByClass(RadarMR.class);

        // 1. 指明输入
        String[] hadoopArgs = new GenericOptionsParser(configuration, args).getRemainingArgs();
        if (hadoopArgs.length < 2) {
            System.err.println("Args is Error, Usage mymovielens <inpath> <inpath> [ <in> ...] <outpath>");
            System.exit(2);
        }
        for (int i = 0; i < hadoopArgs.length - 1; i++) {
            Path inputPath = new Path(hadoopArgs[i]);
            FileInputFormat.addInputPath(job, inputPath);
        }
        job.setInputFormatClass(TextInputFormat.class);

        // 2. 指明 Mapper 类
        job.setMapperClass(RadarMR.RadarMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(MRBean.class);

        // 3、4、5 制定分区、排序、规约 在此都使用默认

        // 6. 指明 Reducer
//        job.setNumReduceTasks(2);     // 不设置 默认为 1
//        job.setNumReduceTasks(0);     // 只看 Mapper 的输出
        job.setReducerClass(RadarMR.RadarReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(MRBean.class);

        // 7. 指定输出
        Path outPath = new Path(hadoopArgs[hadoopArgs.length - 1]);
        File outFile = new File(hadoopArgs[hadoopArgs.length - 1]);
        if (outFile.exists()) {
            outFile.delete();
        }
        FileOutputFormat.setOutputPath(job, outPath);
        job.setOutputFormatClass(TextOutputFormat.class);

        // 8. 提交 MR Job
        boolean resultCompletion = job.waitForCompletion(true);
        boolean resultSuccessful = job.isSuccessful();
        System.exit(resultCompletion && resultSuccessful ? 0 : 1);

    }
}
