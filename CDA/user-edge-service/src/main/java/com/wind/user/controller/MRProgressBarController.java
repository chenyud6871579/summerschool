package com.wind.user.controller;

import com.wind.user.thrift.ServiceProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MRProgressBarController {
    @Resource
    private ServiceProvider serviceProvider;

    private static int mrProgress = 0;
    private static String mrMessage = "";

    @RequestMapping("/mr/zero")
    @ResponseBody
    public String zeroSpiderProgress(){
        mrProgress = 0;
        mrMessage = "";
        return "重置完成";
    }

    @RequestMapping("/mr/how")
    @ResponseBody
    public List<String> getSpiderProgress(){
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(mrProgress));
        list.add(mrMessage);
        return list;
    }

    @RequestMapping("/mr/add")
    @ResponseBody
    public String addSpiderProgress(
            @RequestParam("add") int add,
            @RequestParam("message") String message){
        mrProgress += add;

        mrProgress = mrProgress>100?100:mrProgress;

        mrMessage = message;
        return "已完成添加";
    }
}
