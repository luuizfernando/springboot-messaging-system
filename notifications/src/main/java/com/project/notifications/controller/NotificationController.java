package com.project.notifications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.notifications.domain.Notification;
import com.project.notifications.service.NotificationService;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService service;
 
    @GetMapping("history/{userId}")
    public ResponseEntity<Notification> findById(@RequestParam Long id) {
        Notification msg = service.getNotificationByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
    
}