package com.wind.user;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(value = "com.wind.service.thrift.data")
public class EdgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdgeApplication.class,args);
    }
}
