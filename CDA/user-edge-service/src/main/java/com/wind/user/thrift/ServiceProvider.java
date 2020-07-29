package com.wind.user.thrift;

import com.wind.service.thrift.data.UserService;
import com.wind.thrift.spider.SpiderService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {
    @Value("${thrift.spider.ip}")
    private String spiderServerIp;

    @Value("${thrift.spider.port}")
    private int spiderServerPort;

    @Value("${thrift.user.ip}")
    private String userServerIp;

    @Value("${thrift.user.port}")
    private int userServerPort;

    //声明枚举类型，完成服务类型的区分
    private enum ServiceType {
        USER,
        SPIDER
    }

    // 获取远程服务的代码 -> ip port 服务类型信息
    // 获取远程服务 == 返回类型 -- 泛型
    public <T> T getService(String ip, int port, ServiceType serverType) {
        // RPC框架时， 需要一个socket、transport、protocol， 服务端和客户端需要保持一致

        // 1. 声明一个Socket， 用来连接ServerSocket
        TSocket socket = null;
        try {
            socket = new TSocket(ip, port, 60000);
        } catch (Exception e) {
            System.out.println("连接 serverSocket超时");
        }

        // 2. 生成一个传输方式对象 -- 基于 Socket 连接建立一个帧传输对象
        TTransport transport = new TFramedTransport(socket);
        // 开启帧传输
        try {
            transport.open();
        } catch (TTransportException e) {
            System.out.printf("开启帧传输时出现错误");
            e.printStackTrace();
            return null;
        }
        // 3. 指定传输发送的协议
        TProtocol protocol = new TBinaryProtocol(transport);

        // 4. 获取服务的客户端
        TServiceClient result = null;
        // 判断服务类型，并根据服务类型返回不同的客户端
        switch (serverType) {
            case USER:
                result = new UserService.Client(protocol);
                break;
            case SPIDER:
                result = new SpiderService.Client(protocol);
                break;
        }
        return (T) result;
    }

    //获取用户服务 ThriftServer 的用户客户端
    public UserService.Client getUserService() {
        return getService(userServerIp, userServerPort, ServiceType.USER);
    }

    public SpiderService.Client getSpiderService(){
        return getService(spiderServerIp,spiderServerPort,ServiceType.SPIDER);
    }


}
