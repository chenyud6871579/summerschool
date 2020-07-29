package com.wind.user.controller;

import com.wind.service.thrift.windmr.WindMRService;
import com.wind.user.thrift.ServiceProvider;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MRController {

    @Resource
    private ServiceProvider serviceProvider;

    @RequestMapping("runmapreduce")
    @ResponseBody
    public String runMapReduce() throws TException {
        WindMRService.Iface mrService = serviceProvider.getMRService();
        mrService.runMapReduce();
        return "完成MR";
    }
}
