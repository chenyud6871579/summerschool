package com.test.user.dao.impl;

import com.test.user.dao.MovieDao;
import com.test.thrift.data.Movie;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDaoImpl implements MovieDao {

    private String ipAddress = "192.168.241.7";
    private int ipHost = 27017;
    private String databaseName = "douban";
    private String tableName = "teamwork";
    private String keyName = "name";

    @Override
    public Movie getMovie(){
        String name = "我不是药神";
        Movie movie = new Movie(name,2376,9.5);
        return movie;
    }

    @Override
    public List<Movie> getMovieList() {

        List<Movie> movieList = new ArrayList<>();

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
                System.out.println(document.getString(keyName));
                String movieName = document.getString("name");
                int commentNumber = Integer.valueOf(document.getString("num"));
                double movieStars = Double.valueOf(document.getString("score"));
                Movie movie = new Movie(movieName,commentNumber,movieStars);
                movieList.add(movie);
            }
        }catch (MongoException e){
            System.out.printf("SOMETHING WRONG");
        }finally {
            //关闭连接
            mongoClient.close();
        }

        return movieList;
    }
}
