package com.test.user.dao;

import com.test.thrift.data.Movie;
//import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDao {
    Movie getMovie();
    List<Movie> getMovieList();
}
