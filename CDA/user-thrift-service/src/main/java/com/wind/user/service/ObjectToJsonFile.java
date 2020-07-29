package com.wind.user.service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.wind.service.thrift.data.DataType;
import com.wind.user.bean.OutDataBean;
import com.wind.user.util.MongoUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ObjectToJsonFile {

    @Value("${mymongo.address}")
    private static String ipAddress;
    @Value("${mymongo.host}")
    private static int ipHost;
    @Value("${mymongo.database}")
    private static String databaseName;
    @Value("${mymongo.container.beijing}")
    private static String beijingTable;
    @Value("${mymongo.container.china}")
    private static String chinaTable;
    @Value("${mymongo.container.globe}")
    private static String globeTable;

    public static String toJsonFile(){

        for(DataType type : DataType.values()){
            String fileName = null;

            if (type == DataType.BEIJING) {
                fileName = beijingTable;
            } else if (type == DataType.GLOBE) {
                fileName = globeTable;
            } else {
                fileName = chinaTable;
            }

            Map<String, OutDataBean> mapResult = new HashMap<>();

            MongoCollection<Document> mongoCollection = MongoUtil.getCollection(type);

            //获取文档 FindItersble 是一个迭代器，通过他来遍历文档
            FindIterable<Document> documents = mongoCollection.find();

            for(Document document : documents){

            }
        }

        return "完成";
    }


}
