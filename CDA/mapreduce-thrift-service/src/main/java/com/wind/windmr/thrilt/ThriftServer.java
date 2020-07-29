package com.wind.windmr.thrilt;


import com.wind.service.thrift.windmr.WindMRService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

// Java 实现ThriftServer，并对外提供UserService 的功能，并伴生（Springboot项目）启动
@Configuration
public class ThriftServer {

    // 从配置文件获取端口号
    @Value("${service.port}")
    private int servicePort;

    @Resource
    private WindMRService.Iface windMRService;


    @PostConstruct  // 当前类实例化后自动执行
    public void startThriftServer(){
        // 1. 创建一个 ServerSocket
        TNonblockingServerSocket serverSocket = null;
        try {
            serverSocket = new TNonblockingServerSocket(servicePort);
        } catch (TTransportException e) {
            e.printStackTrace();
            return;
        }

        // 2. 创建一个传输方式
        TFramedTransport.Factory transportFactory = new TFramedTransport.Factory();

        // 3. 创建一个传输协议
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();

        // 4. 创建处理器
        TProcessor processor = new WindMRService.Processor <>(windMRService);

        // 5. 创建服务器
        // 5-1 创建 ThriftServer 的参数对象，放入上述参数
        TNonblockingServer.Args args = new TNonblockingServer.Args(serverSocket);
        args.transportFactory(transportFactory);
        args.protocolFactory(protocolFactory);
        args.processor(processor);
        TServer server = new TNonblockingServer(args);

        // 6.启动服务器
        System.out.println("MapReduce Service start");
        server.serve();
        System.out.println("MapReduce Service end");


    }



}
