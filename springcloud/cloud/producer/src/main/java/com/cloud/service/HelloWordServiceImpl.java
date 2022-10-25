package com.cloud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloWordServiceImpl implements HelloWorldService{

    @Value("${server.port}")
    Integer port;
    @Override
    public String sayHello(String name) {
        return "Hello,"+name+",port:"+port;
    }
}
