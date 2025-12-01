package com.project.notifications.service;

import java.util.List;

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

    public void saveForUser(Long userId, String messageContent) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setText(messageContent);
        repository.save(notification);
    }

    public List<Notification> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

}
