package com.rabbit.rabbitmq.simplequeue;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;
@RabbitListener(queues = "simple-queue")
@Component
public class SimpleQueueLisener {

    @RabbitHandler
    public void process(String msg, Channel channel, Message message){
        System.out.println("simple-queue msg is: "+msg);
    }


}
