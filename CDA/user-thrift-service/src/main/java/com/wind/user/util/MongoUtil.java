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
    private String ipAddress;
    @Value("${mymongo.host}")
    private int ipHost;
    @Value("${mymongo.database}")
    private String databaseName;
    @Value("${mymongo.container.beijing}")
    private String beijingTable;
    @Value("${mymongo.container.china}")
    private String chinaTable;
    @Value("${mymongo.container.globe}")
    private String globeTable;

    public MongoDatabase getDataBase(DataType type){



        System.out.println("开始连接 MongoDB");
        MongoClient mongoClient = new MongoClient(ipAddress, ipHost);
        System.out.println("连接成功");
        return mongoClient.getDatabase(databaseName);
    }

    public MongoCollection<Document> getCollection(DataType type){
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
