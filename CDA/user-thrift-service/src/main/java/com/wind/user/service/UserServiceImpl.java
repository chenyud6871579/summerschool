package com.wind.user.service;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.wind.service.thrift.data.DataBlock;
import com.wind.service.thrift.data.DataType;
import com.wind.service.thrift.data.UserService;
import com.wind.user.bean.OutDataBean;
import com.wind.user.util.MongoUtil;
import org.apache.thrift.TException;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class UserServiceImpl implements UserService.Iface {

    @Resource
    MongoUtil mongoUtil;


    @Override
    public List<DataBlock> getDataBlock(DataType type){
        return null;
    }

    @Override
    public String getData(DataType type) {


        Map<String, OutDataBean> mapResult = new HashMap<>();

        //获取集合
        MongoCollection<Document> Test = mongoUtil.getCollection(type);

        //获取文档 FindItersble 是一个迭代器，通过他来遍历文档
        FindIterable<Document> documents = Test.find();

        //循环遍历
        for (Document document : documents) {
            OutDataBean outDataBean = new OutDataBean();
            outDataBean.setName((String) document.get("_id"));
            outDataBean.setCode((String) document.get("name"));
            outDataBean.setConfirmed((List<Integer>) document.get("confirmed"));
            outDataBean.setDate((List<Integer>) document.get("date"));
            outDataBean.setSuspected((List<Integer>) document.get("suspected"));
            outDataBean.setCured((List<Integer>) document.get("cured"));
            outDataBean.setDead((List<Integer>) document.get("dead"));
            List<Double> radarList = document.getList("radar",Double.class);
            outDataBean.setRadarList(radarList);
            mapResult.put((String) document.get("_id"), outDataBean);



        }
        return JSON.toJSONString(mapResult);
    }
}