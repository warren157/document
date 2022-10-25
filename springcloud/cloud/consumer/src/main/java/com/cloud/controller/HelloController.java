package com.cloud.controller;

import com.cloud.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    HelloWorldService helloWorldService;

    @GetMapping("/getHello")
    @ResponseBody
    public String sayHello(String name) {
        //解析hellowordservice上面的feignclient注解
        //然后在解析sayHello上的getMapping注解
        //r然后封装给http://ip:port/sayHello?name=name
        return helloWorldService.sayHello(name);
    }
}
