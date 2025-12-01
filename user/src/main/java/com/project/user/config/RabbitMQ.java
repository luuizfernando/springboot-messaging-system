package com.project.user.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ {
    
    private final String queueName = "notifications.queue";

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

}