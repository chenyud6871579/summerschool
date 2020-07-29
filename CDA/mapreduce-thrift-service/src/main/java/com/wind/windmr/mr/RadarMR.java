package com.wind.windmr.mr;

import com.alibaba.fastjson.JSON;
import com.wind.user.bean.OutDataBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

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
            extends Mapper<LongWritable, Text, Text, Text> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            OutDataBean outDataBean = (OutDataBean) JSON.parse(line);
//            context.write();
        }
    }
}
