package com.rabbit.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "simple-queue-b")
public class QueueBLisener {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("simple-queue-b msg is :"+msg);
    }
}
