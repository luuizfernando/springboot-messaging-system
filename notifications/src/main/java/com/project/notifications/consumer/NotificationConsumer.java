package com.project.notifications.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.project.notifications.service.NotificationService;

@Component
public class NotificationConsumer {
    
    @Autowired
    private NotificationService service;

    @RabbitListener(queues = "notifications.queue")
    public void listenNotificationQueue(@Payload String msg) {
        System.out.println("Mensagem recebida: " + msg);

        service.processAndSave(msg);
    }

}