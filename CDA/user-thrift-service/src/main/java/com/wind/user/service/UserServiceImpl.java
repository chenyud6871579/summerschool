package com.wind.user.service;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.javafx.collections.MappingChange;
import com.wind.service.thrift.data.DataBlock;
import com.wind.service.thrift.data.DataType;
import com.wind.service.thrift.data.UserService;
import com.wind.user.bean.OutDataBean;
import org.apache.thrift.TException;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.aggregation.Aggregation;
//import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
//import org.springframework.data.mongodb.core.aggregation.AggregationResults;
//import org.springframework.data.mongodb.core.query.Query;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.*;

@Component
public class UserServiceImpl implements UserService.Iface {

//    mymongo.address=192.168.241.7
//    mymongo.host=27017
//    mymongo.database=covid-19
//    mymongo.container=Beijing

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
    private String keyName = "city";

//    private Class target;

//    @Resource
//    private DataDao<BeijingData> dataDaoBeijing;
//    @Resource
//    private BeijingDao beijingDao;
//    @Resource
//    private MongoTemplate mongoTemplate;
//    @Resource
//    private DataDao<ChinaData> dataDaoChina;
//    @Resource
//    private DataDao<GlobeData> dataDaoGlobe;

    @Override
    public List<DataBlock> getDataBlock(DataType type) throws TException {
//        System.out.println("get in");
//        if(type == DataType.BEIJING){
////            List<BeijingData> beijingData = beijingDao.getAll();
//            List<BeijingData> beijingData = dataDaoBeijing.findAll(BeijingData.class);
//            for (BeijingData item : beijingData){
//                System.out.printf(item.toString());
//            }
//        }
        return null;
    }

    @Override
    public String getData(DataType type){

        String tableName = null;

        if(type == DataType.BEIJING){
            tableName = beijingTable;
        }else if (type == DataType.GLOBE){
            tableName = globeTable;
        }else {
            tableName = chinaTable;
        }

        List<OutDataBean> result = new ArrayList<>();

        Map<String,OutDataBean> mapResult = new HashMap<>();

        System.out.println("开始连接 MongoDB");
        MongoClient mongoClient = new MongoClient(ipAddress,ipHost);
        System.out.println("连接成功");
        try {

            MongoDatabase admindb = mongoClient.getDatabase(databaseName);


            //获取集合
            MongoCollection<Document> Test = admindb.getCollection(tableName);

            //获取文档 FindItersble 是一个迭代器，通过他来遍历文档
            FindIterable<Document> documents = Test.find();

            //循环遍历
            for (Document document:documents) {
                OutDataBean outDataBean = new OutDataBean();
                outDataBean.setName((String)document.get("_id"));
                outDataBean.setCode((String)document.get("name"));
                outDataBean.setConfirmed((List<Integer>) document.get("confirmed"));
                outDataBean.setDate((List<Integer>) document.get("date"));
                outDataBean.setSuspected((List<Integer>) document.get("suspected"));
                outDataBean.setCured((List<Integer>) document.get("cured"));
                outDataBean.setDead((List<Integer>) document.get("dead"));
                System.out.println(JSON.toJSONString(outDataBean));
                result.add(outDataBean);
                mapResult.put((String)document.get("_id"),outDataBean);
//                for (String item:document.keySet()){
//                    System.out.println(item + " : " + document.get(item));
//                }
//                System.out.println(document.get("code"));
                System.out.println("--------------------------");
            }
        }catch (MongoException e){
            System.out.printf("SOMETHING WRONG");
        }finally {
            //关闭连接
            mongoClient.close();
        }

        return JSON.toJSONString(mapResult);
    }

//    @Override
//    public String getData(DataType type) throws TException {
//        //封装查询条件
//        List<AggregationOperation> operations = new ArrayList<>();
//        operations.add(Aggregation.limit(100));
//
//        Aggregation aggregation1 = Aggregation.newAggregation(
//                operations
//        );
////        Aggregation aggregation = Aggregation.newAggregation(
////                Aggregation.limit(10)
////        );
////        AggregationResults<BasicDBObject> outputTypeCount1 =
////            mongoTemplate.aggregate(aggregation1,
////                    "test",
////                    BasicDBObject.class);
//        List<BeijingTem> allBeijing = mongoTemplate.findAll(BeijingTem.class, "Beijing");
//        System.out.println(JSON.toJSONString(allBeijing));
//        System.out.println(mongoTemplate.exists( new Query(),"Beijing"));
//
//        List<BasicDBObject> result = new ArrayList<>();
//        List<Integer> radar = new ArrayList<Integer>();
//        radar.add(1);
//        radar.add(2);
//        radar.add(3);
//        radar.add(4);
//        radar.add(5);
//        radar.add(5);
////        mongoTemplate.save(radar,"radar");
//
////        for (Iterator<BasicDBObject> iterator = outputTypeCount1.iterator(); iterator.hasNext(); ) {
////            BasicDBObject obj = iterator.next();
////            obj.put("radar",radar);
////            Object tem = obj.get("confirmed.value");
////            result.add(obj);
////        }
////        return JSON.toJSONString(result);
//
////        mongoTemplate.findAll()
//        return JSON.toJSONString(radar);
//    }
}
