package com.project.notifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.notifications.domain.Notification;
import com.project.notifications.repository.NotificationRepository;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository repository;

    public void processAndSave(String messageContent) {
        Notification notification = new Notification();
        notification.setText(messageContent);        
        repository.save(notification);
    }

    public Notification getNotificationByUserId(Long id) {
        return repository.findById(id).orElse(null);
    }

}