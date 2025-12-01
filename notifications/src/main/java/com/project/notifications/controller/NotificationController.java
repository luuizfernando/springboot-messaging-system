package com.project.notifications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.notifications.domain.Notification;
import com.project.notifications.service.NotificationService;
import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService service;
 
    @GetMapping("history/{userId}")
    public ResponseEntity<List<Notification>> findByUserId(@PathVariable Long userId) {
        List<Notification> msgs = service.findByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(msgs);
    }
    
}