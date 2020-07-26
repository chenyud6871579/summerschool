package com.wind.user.controller;

import com.wind.service.thrift.data.DataBlock;
import com.wind.service.thrift.data.DataType;
import com.wind.service.thrift.data.UserService;
import com.wind.user.thrift.ServiceProvider;


import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private ServiceProvider serviceProvider;

    @RequestMapping(value = "/beijing", method = RequestMethod.GET)
    @ResponseBody
    public List<DataBlock> getBeijing() throws TException {
        UserService.Iface  userService = serviceProvider.getUserService();
//        List<DataBlock> result = null;
//        try {
//            result = userService.getDataBlock(DataType.BEIJING);
//        } catch (TException e) {
//            System.out.println("获取失败");
//            e.printStackTrace();
//            return null;
//        }
        List<DataBlock> result = userService.getDataBlock(DataType.BEIJING);
        return result;
    }

    @RequestMapping(value = "/shanghai")
    @ResponseBody
    public String getShanghai() throws TException {
//        UserService.Iface userService = serviceProvider.getUserService();
        return "hi";
    }
}
