package com.test.data.service;

import com.test.user.dao.MovieDao;
import com.test.thrift.data.DataService;
import com.test.thrift.data.Movie;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class  DataServiceImpl implements DataService.Iface{

    @Resource
    private MovieDao movieDao;

    @Override
    public List<Movie> getMovieList() throws TException {
        return movieDao.getMovieList();
    }
}
