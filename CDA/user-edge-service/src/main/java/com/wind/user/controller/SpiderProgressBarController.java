package com.wind.user.controller;

import com.wind.user.thrift.ServiceProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Controller
@RequestMapping("/progress")
public class SpiderProgressBarController {

    @Resource
    private ServiceProvider serviceProvider;

    private static int spiderProgress = 0;
    private static String spiderMessage = "";

    @RequestMapping("/spider/zero")
    @ResponseBody
    public String zeroSpiderProgress(){
        spiderProgress = 0;
        spiderMessage = "";
        return "重置完成";
    }

    @RequestMapping("/spider/how")
    @ResponseBody
    public List<String> getSpiderProgress(){
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(spiderProgress));
        list.add(spiderMessage);
        return list;
    }

    @RequestMapping("/spider/add")
    @ResponseBody
    public String addSpiderProgress(
            @RequestParam("add") int add,
            @RequestParam("message") String message){
        spiderProgress += add;

        spiderProgress = spiderProgress>100?100:spiderProgress;

        spiderMessage = message;
        return "已完成添加";
    }


}
