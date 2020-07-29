package com.wind.user.controller;

import com.wind.thrift.spider.SpiderService;
import com.wind.user.thrift.ServiceProvider;
import org.apache.thrift.TException;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class SpiderController {

    @Resource
    private ServiceProvider serviceProvider;

    @RequestMapping("/spider")
    @ResponseBody
    public String runSpider() throws TException {
        SpiderService.Iface spiderService = serviceProvider.getSpiderService();
        spiderService.runSpider();
        return "数据爬取完毕！";
    }
}
