package com.wind.user.controller;

import com.wind.service.thrift.windmr.WindMRService;
import com.wind.used.util.WindUtil;
import com.wind.user.thrift.ServiceProvider;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/mr")
public class MRController {

    @Resource
    private ServiceProvider serviceProvider;


    @RequestMapping("/runmr")
    @ResponseBody
    public String runMapReduce() throws TException {
        WindUtil.pushProgressBar(10,"first");
        WindMRService.Iface mrService = serviceProvider.getMRService();
        mrService.runMapReduce();
        return "完成MR";
    }

    @RequestMapping("/localfile")
    @ResponseBody
    public String createLocalFile() throws TException {
        WindMRService.Iface mrService = serviceProvider.getMRService();
        mrService.getLocalData();
        return "完成本地文件生成";
    }

}
