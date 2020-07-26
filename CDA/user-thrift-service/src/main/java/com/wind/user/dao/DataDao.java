package com.wind.user.dao;

import com.wind.user.pojo.BaseData;
import org.springframework.stereotype.Component;

import java.util.List;

public interface DataDao<T> {
    List<T> findAll(Class c);
}
