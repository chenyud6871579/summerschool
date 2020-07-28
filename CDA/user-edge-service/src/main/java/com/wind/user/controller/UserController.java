package com.wind.user.controller;

import com.wind.service.thrift.data.DataBlock;
import com.wind.service.thrift.data.DataType;
import com.wind.service.thrift.data.UserService;
import com.wind.user.thrift.ServiceProvider;


import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private ServiceProvider serviceProvider;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    public String getBeijing(@RequestParam("type") String type) throws TException {
        DataType datatype = null;
        if (type.equals("beijing")){
            datatype = DataType.BEIJING;
        }else if (type.equals("globe")){
            datatype = DataType.GLOBE;
        }else if (type.equals("china")){
            datatype = DataType.CHINA;
        }else {
            return "WRONG INPUT";
        }
        UserService.Iface userService = serviceProvider.getUserService();
        String result = userService.getData(datatype);
        return result;
    }

    @RequestMapping(value = "/shanghai")
    @ResponseBody
    public String getShanghai() throws TException {
        return "hi";
    }
}
