# Springboot集成rabbitmq



## rabbitmq安装

```
docker安装
1.docker pull rabbitmq:management
2.docker run --name rabbitmq -d -p 15672:15672 -p 5672:5672 rabbitmq:management
```

### 管理界面[http://localhost:15672]

## springboot中使用rabbitmq

### 1.pom

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>

```

### 2.创建queue

```
1.可以在管理界面创建queue
2.可以在springboot指定Queue
package com.rabbit.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue queue(){
        return new Queue("simple-queue");
    }

    @Bean
    public Queue queueB(){
        return new Queue("simple-queue-b");
    }

    @Bean
    public FanoutExchange exchange(){
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    public Binding binding(){

        return BindingBuilder.bind(queue()).to(exchange());
    }

    @Bean
    public Binding bindingB(){

        return BindingBuilder.bind(queueB()).to(exchange());
    }
}

```

### 3.springboot

```
@Component
@RabbitListener(queues = "simple-queue-b")
public class QueueBLisener {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("simple-queue-b msg is :"+msg);
    }
}
```











![image-20221026162007220](/Users/wangrui/Library/Application Support/typora-user-images/image-20221026162007220.png)

```
生产者发送数据到交换机中，然后通过路由分发到各个队列中，然后在springboot中监听各个队列，处理相应的业务
```



## 场景

```
1.解耦：A团队开发短信系统，B团队的系统有注册，要发送短信内容。http请求可能时间很长， 
			引入mq，{"phone":"15755148661","content":"8661","source":"shop"} -> 短信系统监听这个queue，然后进行业务逻辑处理
			放在：redis -> 用户收到短信 -> 填写验证码 -> 提交 -> 后台根据 phone+source 中到redis中查看缓存的验证码是否一样
2.消峰：
```





## 问题

```
1.为什么需要mq
2.如果producer在发送到exchange过程中数据丢失怎么办
3.如果exchange到queue过程中丢失怎么办
4.如果springboot监听消费过程中数据丢失怎么办
```

