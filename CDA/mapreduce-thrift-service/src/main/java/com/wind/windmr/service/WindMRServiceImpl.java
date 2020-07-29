package com.wind.windmr.service;

import com.wind.service.thrift.windmr.WindMRService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

@Service
public class WindMRServiceImpl implements WindMRService.Iface {
    @Override
    public void getLocalData() throws TException {

    }

    @Override
    public void runMapReduce() throws TException {
        System.out.println("hi");
    }
}
