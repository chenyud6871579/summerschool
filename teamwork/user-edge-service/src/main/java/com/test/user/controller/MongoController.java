package com.test.user.controller;

import com.test.thrift.data.DataService;
import com.test.user.dao.MovieDao;
import com.test.thrift.data.Movie;
import com.test.user.thrift.ServiceProvider;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class MongoController {
    @Resource
    private MovieDao movieDao;
    @Resource
    private ServiceProvider serviceProvider;


    @RequestMapping("/testmovie")
    @ResponseBody
    public List<Movie> testMovie() throws TException {
        DataService.Iface dataService =  serviceProvider.getUserService();
        return dataService.getMovieList();
    }

    @RequestMapping("/movie")
    @ResponseBody
    public Movie getOneMovie(){
        return movieDao.getMovie();
    }
    @RequestMapping("/movielist")
    @ResponseBody
    public List<Movie> getMovieList(){
        System.out.println("getList");
        return movieDao.getMovieList();
    }
}
