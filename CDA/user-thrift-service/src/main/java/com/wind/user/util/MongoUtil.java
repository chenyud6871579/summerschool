package com.wind.user.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.wind.service.thrift.data.DataType;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class MongoUtil {
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

    public static MongoDatabase getDataBase(DataType type){



        System.out.println("开始连接 MongoDB");
        MongoClient mongoClient = new MongoClient(ipAddress, ipHost);
        System.out.println("连接成功");
        return mongoClient.getDatabase(databaseName);
    }

    public static MongoCollection<Document> getCollection(DataType type){
        String tableName = null;

        if (type == DataType.BEIJING) {
            tableName = beijingTable;
        } else if (type == DataType.GLOBE) {
            tableName = globeTable;
        } else {
            tableName = chinaTable;
        }
        return getDataBase(type).getCollection(tableName);
    }
}
