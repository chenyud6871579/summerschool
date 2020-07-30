package com.wind.windmr.service;

import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.wind.service.thrift.data.DataType;
import com.wind.service.thrift.windmr.WindMRService;
import com.wind.used.util.WindUtil;
import com.wind.windmr.mr.RadarMR;
import org.apache.thrift.TException;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class WindMRServiceImpl implements WindMRService.Iface {

    @Value("${mymongo1.address}")
    private String ipAddress;
    @Value("${mymongo1.host}")
    private int ipHost;
    @Value("${mymongo1.database}")
    private String databaseName;
    @Value("${mymongo1.container.beijing}")
    private String beijingTable;
    @Value("${mymongo1.container.china}")
    private String chinaTable;
    @Value("${mymongo1.container.globe}")
    private String globeTable;

    @Resource
    private RadarMR radarMR;

    @Override
    public void getLocalData() throws TException {

        MongoClient mongoClient = new MongoClient(ipAddress, ipHost);
        MongoDatabase database = mongoClient.getDatabase(databaseName);

        String userDir = System.getProperty("user.dir");
        System.out.println("用户的当前工作目录:"+userDir);
        String outPutFileString = userDir + "\\mapreduce-thrift-service\\input\\";
        System.out.println("outPutFileString : " + outPutFileString);
        String fileName;

        String tableName;
        String keyName = "";
        String folder = "";

        for(DataType dataType : DataType.values()){
            if (dataType == DataType.BEIJING) {
                tableName = beijingTable;
                fileName = beijingTable;
                keyName = "_id";
                folder = "beijing\\";
            } else if (dataType == DataType.GLOBE) {
                tableName = globeTable;
                fileName = globeTable;
                keyName = "name";
                folder = "globe\\";
            } else {
                tableName = chinaTable;
                fileName = chinaTable;
                keyName = "_id";
                folder = "china\\";
            }
            MongoCollection<Document> mongoCollection = database.getCollection(tableName);
            //获取文档 FindItersble 是一个迭代器，通过他来遍历文档
            FindIterable<Document> documents = mongoCollection.find();

            // 提供FileWriter的对象，用于数据写出
            // 第二个参数，是否追加
            FileWriter fw = null;
            try {
                // 提供File类对象，指明写出文件
                File file = new File(outPutFileString + folder + fileName);
                if(file.exists())
                    file.delete();
                fw = new FileWriter(file, true);
                // 写出
                for(Document document : documents){
//                    System.out.println(JSON.toJSONString(document));
//                    fw.write(JSON.toJSONString(document));
                    String outString = "";
                    outString = (String) document.get(keyName) + "::" +
                            JSON.toJSONString(document.get("confirmed")) + "::" +
                            JSON.toJSONString(document.get("cured")) + "::" +
                            JSON.toJSONString(document.get("dead")) + "::" +
                            JSON.toJSONString(document.get("suspected")) + "\n";
                    fw.write(outString);
                    fw.flush();
                }
                System.out.println(tableName + " end");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(fw != null)
                        fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void runMapReduce() throws TException {
        String [] location = {"globe","china"};
        for (String item : location){
            String [] args = {"mapreduce-thrift-service/input/"+item,"mapreduce-thrift-service/output/"+item};
            String blockName = "";
            if (item.equals("globe")){
                blockName = "国外";
            }else if (item.equals("china")){
                blockName = "国内";
            }
            WindUtil.pushProgressBar(5,"开始处理"+blockName+"数据");
            try {
                radarMR.runRadarMR(args);
            } catch (Exception e) {
                WindUtil.pushProgressBar(-1,"出现错误！");
            }
        }
        WindUtil.pushProgressBar(0,"MapReduce 运行结束");

    }

}
