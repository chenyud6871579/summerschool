package com.wind.windmr.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.wind.service.thrift.data.DataType;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MongoUtil {
    @Value("${mymongo.address}")
    private String ipAddressUtil;
    @Value("${mymongo.host}")
    private int ipHostUtil;
    @Value("${mymongo.database}")
    private String databaseNameUtil;
    @Value("${mymongo.container.beijing}")
    private String beijingTableUtil;
    @Value("${mymongo.container.china}")
    private String chinaTableUtil;
    @Value("${mymongo.container.globe}")
    private String globeTableUtil;

    public MongoDatabase getDataBase(DataType type){



        System.out.println("开始连接 MongoDB");
        MongoClient mongoClient = new MongoClient(ipAddressUtil, ipHostUtil);
        System.out.println("连接成功");
        return mongoClient.getDatabase(databaseNameUtil);
    }

    public MongoCollection<Document> getCollection(DataType type){
        String tableName = null;

        if (type == DataType.BEIJING) {
            tableName = beijingTableUtil;
        } else if (type == DataType.GLOBE) {
            tableName = globeTableUtil;
        } else {
            tableName = chinaTableUtil;
        }
        return getDataBase(type).getCollection(tableName);
    }

    public String getIpAddressUtil() {
        return ipAddressUtil;
    }

    public void setIpAddressUtil(String ipAddressUtil) {
        this.ipAddressUtil = ipAddressUtil;
    }

    public int getIpHostUtil() {
        return ipHostUtil;
    }

    public void setIpHostUtil(int ipHostUtil) {
        this.ipHostUtil = ipHostUtil;
    }

    public String getDatabaseNameUtil() {
        return databaseNameUtil;
    }

    public void setDatabaseNameUtil(String databaseNameUtil) {
        this.databaseNameUtil = databaseNameUtil;
    }

    public String getBeijingTableUtil() {
        return beijingTableUtil;
    }

    public void setBeijingTableUtil(String beijingTableUtil) {
        this.beijingTableUtil = beijingTableUtil;
    }

    public String getChinaTableUtil() {
        return chinaTableUtil;
    }

    public void setChinaTableUtil(String chinaTableUtil) {
        this.chinaTableUtil = chinaTableUtil;
    }

    public String getGlobeTableUtil() {
        return globeTableUtil;
    }

    public void setGlobeTableUtil(String globeTableUtil) {
        this.globeTableUtil = globeTableUtil;
    }
}
