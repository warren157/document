package com.cloud.controller;

import com.cloud.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    HelloWorldService helloWorldService;
    @GetMapping("/sayHello")
    @ResponseBody
    public String sayHello(@RequestParam("name") String name) {
        return helloWorldService.sayHello(name);
    }
}
