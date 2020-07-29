package com.wind.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/progress")
public class ProgressBarController {
    private static int spiderProgress = 0;
    private static String spiderMessage = "";

    @RequestMapping("/spider/zero")
    public String zeroSpiderProgress(){
        spiderProgress = 0;
        spiderMessage = "";
        return "重置完成";
    }

    @RequestMapping("/spider/how")
    @ResponseBody
    public String getSpiderProgress(){
        Map<Integer,String> map = new HashMap<>();
        map.put(spiderProgress,spiderMessage);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/spider/add")
    @ResponseBody
    public String addSpiderProgress(
            @RequestParam("add") int add,
            @RequestParam("message") String message){
        spiderProgress += add;
        spiderMessage = message;
        return "已完成添加";
    }




}
