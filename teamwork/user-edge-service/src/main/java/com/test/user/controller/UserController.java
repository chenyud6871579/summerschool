package com.test.user.controller;

import com.test.thrift.douban.DoubanService;
import com.test.user.thrift.ServiceProvider;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping("/user")
@Controller
public class UserController {
    @Resource   // 注入 ThriftClient
    private ServiceProvider serviceProvider;

    @Value("${douban.num}")
    private int spiderNum;
    @Value("${douban.ip}")
    private String spiderIp;
    @Value("${douban.db}")
    private String spiderDb;
    @Value("${douban.table}")
    private String spiderTable;

    @RequestMapping(value = "/spider", method = RequestMethod.GET)
    @ResponseBody
    public String runSpider(){


        DoubanService.Iface doubanService = serviceProvider.getDoubanService();
        try {
            doubanService.getInfo(spiderNum,spiderIp,spiderDb,spiderTable);
        } catch (TException e) {
            e.printStackTrace();
        }
        return "hi world";
    }

}
