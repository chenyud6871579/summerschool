package com.wind.user.dao.impl;

import com.wind.user.dao.BeijingDao;
import com.wind.user.pojo.BeijingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeijingDaoImpl implements BeijingDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<BeijingData> getAll() {
        return mongoTemplate.findAll(BeijingData.class);
    }
}
