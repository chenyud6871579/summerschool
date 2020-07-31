package com.wind.user.controller;

import com.wind.user.thrift.ServiceProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/progress")
public class MRProgressBarController {

    private static int mrProgress = 0;
    private static String mrMessage = "";

    @RequestMapping("/mr/zero")
    @ResponseBody
    public String zeroMRProgress(){
        mrProgress = 0;
        mrMessage = "";
        return "重置完成";
    }

    @RequestMapping("/mr/how")
    @ResponseBody
    public List<String> getMRProgress(){
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(mrProgress));
        list.add(mrMessage);
        return list;
    }

    @RequestMapping("/mr/add")
    @ResponseBody
    public String addMRProgress(
            @RequestParam("add") int add,
            @RequestParam("message") String message){
        mrProgress += add;

        mrProgress = mrProgress>100?100:mrProgress;

        try {
            mrMessage = URLDecoder.decode(message,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "已完成添加";
    }
}
