package com.wind.user.dao.impl;

import com.wind.user.dao.DataDao;
import com.wind.user.pojo.BaseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataDaoImpl<T> implements DataDao<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<T> findAll(Class c) {
        return mongoTemplate.findAll(c);
    }
}
