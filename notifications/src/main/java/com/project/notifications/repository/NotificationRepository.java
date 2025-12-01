package com.project.notifications.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.notifications.domain.Notification;


@Repository
public interface NotificationRepository extends MongoRepository<Notification, Long> {

    public List<Notification> findByUserId(Long userId);
    
}