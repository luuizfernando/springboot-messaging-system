package com.project.notifications.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.notifications.domain.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, Long> {
    
}