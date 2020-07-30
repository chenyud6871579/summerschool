package com.wind.user.controller;

import com.alibaba.fastjson.JSON;
import com.wind.service.thrift.windmr.WindMRService;
import com.wind.thrift.spider.SpiderService;
import com.wind.used.util.WindUtil;
import com.wind.user.thrift.ServiceProvider;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URLEncoder;

@Controller
public class MRController {

    @Resource
    private ServiceProvider serviceProvider;

    private static String mrFlag = "true";


    @RequestMapping("/mr")
    @ResponseBody
    public String runMapReduce() throws TException {
        if(mrFlag.equals("true")) {
            mrFlag = "false";
//            WindMRService.Iface mrService = serviceProvider.getMRService();
//            mrService.runMapReduce();

//            WindUtil.pushProgressBar(0, "All MapReduce job done!");
            WindUtil.pushProgressBar(0, "结束！");
            mrFlag = "true";
            return "MapReduce 执行完毕";
        }
        return "running";
    }

    @RequestMapping("/mr/localfile")
    @ResponseBody
    public String createLocalFile() throws TException {
        WindMRService.Iface mrService = serviceProvider.getMRService();
        mrService.getLocalData();
        return "完成本地文件生成";
    }

    @RequestMapping("/mr/status")
    @ResponseBody
    public String spiderStatus(){
        return String.valueOf(mrFlag);
    }

}
