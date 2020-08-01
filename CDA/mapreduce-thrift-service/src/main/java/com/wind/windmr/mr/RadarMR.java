package com.wind.windmr.mr;

import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.oracle.xmlns.internal.webservices.jaxws_databinding.XmlOneway;
import com.wind.used.util.WindUtil;
import com.wind.windmr.domain.po.MRBean;
import com.wind.windmr.util.MongoDBInfo;
import com.wind.windmr.util.MongoUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
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
import org.bson.Document;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class RadarMR {

    private static String ipAddress = MongoDBInfo.ipAddress;
    private static int ipHost = MongoDBInfo.ipHost;
    private static String databaseName = MongoDBInfo.databaseName;
    private static String hadoopHome = MongoDBInfo.hadoopHome;
    private static String hadoopTmp = MongoDBInfo.hadoopTmp;
    private static String dataType;

    public static List<Double> getRadarList(long population,
                                            List<Integer> confirmedList,
                                            List<Integer> curedList,
                                            List<Integer> deadList) {
        long total = population;
        int confirmed[] = confirmedList.stream().mapToInt(Integer::valueOf).toArray();
        int cured[] = curedList.stream().mapToInt(Integer::valueOf).toArray();
        int dead[] = deadList.stream().mapToInt(Integer::valueOf).toArray();
        int[] remain = new int[confirmed.length];
        for (int i = 0; i < confirmed.length; i++) {
            remain[i] = confirmed[i] - cured[i] - dead[i];
        }
        int max_value = remain[0];
        int max_index = 0;
        for (int i = 0; i < remain.length; i++) {
            if (remain[i] > max_value) {
                max_value = remain[i];
                max_index = i;
            }
        }

        List<Double> radarList = new ArrayList<>();

        // 健康等级
        double item1 = 1000 * (1 - remain[remain.length - 1] / (1.0 * total)) - 990;
        double result1 = Double.valueOf(String.format("%.1f", item1));
//        System.out.println(result1);

        // 治愈效果
        double item2 = 10.0 * cured[cured.length - 1] / confirmed[confirmed.length - 1];
        double result2 = Double.valueOf(String.format("%.1f", item2));
//        System.out.println(result2);

        // 政府措施
        double x3 = (max_value - remain[remain.length - 1]) / (1.0 * total);
        double item3 = 10.0 / (1 + Math.pow(Math.E, x3 * -5));
        double result3 = Double.valueOf(String.format("%.1f", item3));
//        System.out.println(result3);

        // 公众意识
        double x4 = max_value / (1.0 * total);
        double item4 = 10.0 / (1 + Math.pow(Math.E, x4 * -500));
        double result4 = Double.valueOf(String.format("%.1f", item4));
//        System.out.println(result4);

        // 综合评分
        double item5 = (item1 + item2 + item3 + item4) / 4;
        double result5 = Double.valueOf(String.format("%.1f", item5));
//        System.out.println(result5);


        radarList.add(result5);
        radarList.add(result4);
        radarList.add(result3);
        radarList.add(result2);
        radarList.add(result1);

        return radarList;
    }

    static {
        // 设置宿主机环境变量 HADOOP_USER_NAME == icss
        System.setProperty("HADOOP_USER_NAME", "icss");
        String osInfo = System.getProperty("os.name");
        if (osInfo.toLowerCase().indexOf("windows") != -1) {
            System.setProperty("hadoop.home.dir", hadoopHome);
            System.setProperty("hadooop.tmp.dir", hadoopTmp);

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

            String[] filed = line.split("::");

            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            String dataSourceFilename = inputSplit.getPath().getName();

            if (dataSourceFilename.indexOf("population") != -1) {
                // 是 人口数 文件
                nameText.set(filed[0]);
                mrBean.setPopulation(Long.valueOf(filed[1]));
                mrBean.setFlag("population");
            } else {
                // 是 疫情信息 文件


                nameText.set(filed[0]);
                mrBean.setConfirmed(JSON.parseArray(filed[1], Integer.class));
                mrBean.setCured(JSON.parseArray(filed[2], Integer.class));
                mrBean.setDead(JSON.parseArray(filed[3], Integer.class));
                mrBean.setSuspected(JSON.parseArray(filed[4], Integer.class));
                mrBean.setFlag("database");
            }
            context.write(nameText, mrBean);
        }
    }

    // 2. 完成 reduce
    private static class RadarReducer
            extends Reducer<Text, MRBean, Text, Iterable<Double>> {
        @Override
        protected void reduce(Text nameText, Iterable<MRBean> mrBeans, Context context) throws IOException, InterruptedException {
            MRBean outMRBean = new MRBean();
            for (MRBean mrBean : mrBeans) {
                if (mrBean.getFlag().equals("population")) {
                    // 人口
                    outMRBean.setPopulation(mrBean.getPopulation());
                } else {
                    // 数据库
                    outMRBean.setConfirmed(mrBean.getConfirmed());
                    outMRBean.setCured(mrBean.getCured());
                    outMRBean.setDead(mrBean.getDead());
                    outMRBean.setSuspected(mrBean.getSuspected());
                }
            }
            if (outMRBean.getConfirmed() == null) {
                // 人口有 数据库没有
                return;
            } else if (outMRBean.getPopulation() == 0) {
                // 数据库有 人口没有
                return;
            }

            /*
             * 此后是提供的变量
             */
            long population = outMRBean.getPopulation();
            List<Integer> confirmed = outMRBean.getConfirmed();
            List<Integer> cured = outMRBean.getCured();
            List<Integer> dead = outMRBean.getDead();

            // 接受返回值的参数
            List<Double> radarList = getRadarList(population, confirmed, cured, dead);

            // 更新数据库
            MongoClient mongoClient = new MongoClient(ipAddress, ipHost);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> mongoCollection = database.getCollection(dataType);

            Document updateDocument = new Document();
            updateDocument.append("$set", new Document().append("radar", radarList));
            String blockName = dataType == "Globe_new" ? "name" : "_id";
            Document searchQuery2 = new Document().append(blockName, nameText.toString());
            mongoCollection.updateOne(searchQuery2, updateDocument);

            context.write(nameText, radarList);
        }
    }

    // 3. MR2-Controller
    public static void runRadarMR(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 0. 初始化 MR Job
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "Movie Lens Version 1.0");
        job.setJarByClass(RadarMR.class);

        WindUtil.pushProgressBar(5,"开始初始化 MapReduce 任务...");

        // 1. 指明输入
        String[] hadoopArgs = new GenericOptionsParser(configuration, args).getRemainingArgs();
        if (hadoopArgs.length < 2) {
            System.err.println("Args is Error！");
            return;
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
        job.setOutputValueClass(Iterator.class);

        // 7. 指定输出
        String outString = hadoopArgs[hadoopArgs.length - 1];
        Path outPath = new Path(outString);
        File outFile = new File(outString);
        delFile(outFile);
        if (outString.toLowerCase().indexOf("china") != -1) {
            dataType = "China_new";
        } else if (outString.toLowerCase().indexOf("globe") != -1) {
            dataType = "Globe_new";
        } else if (outString.toLowerCase().indexOf("beijing") != -1) {
            dataType = "Beijing_new";
        }

        FileOutputFormat.setOutputPath(job, outPath);
        job.setOutputFormatClass(TextOutputFormat.class);

        WindUtil.pushProgressBar(10,"MapReduce 配置完成,开始运行...");

        // 8. 提交 MR Job
        boolean resultCompletion = job.waitForCompletion(true);
        boolean resultSuccessful = job.isSuccessful();

        WindUtil.pushProgressBar(20,"MapReduce 执行结束");

//        System.exit(resultCompletion && resultSuccessful ? 0 : 1);
//        System.exit(0);

    }

    static boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }
}
