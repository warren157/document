package com.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="PRODUCER")
@Component
public interface HelloWorldService {

    @GetMapping("/sayHello")
    String sayHello(@RequestParam("name") String name);
    //http://producer{ip}:8999/8899/sayHello?name=name
}
