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
