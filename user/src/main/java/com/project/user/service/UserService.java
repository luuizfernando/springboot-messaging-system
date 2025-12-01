package com.project.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.user.domain.user.User;
import com.project.user.messaging.NotificationEvent;
import com.project.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private final RabbitTemplate template;

    public UserService(RabbitTemplate template) {
        this.template = template;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void sendNotification(Long userId, String payload) {
        repository.findById(userId).ifPresent(user -> {
            List<String> msgs = user.getMessages();
            if (msgs == null) msgs = new ArrayList<>();
            msgs.add(payload);
            user.setMessages(msgs);
            repository.save(user);
        });
        NotificationEvent event = new NotificationEvent(userId, payload);
        template.convertAndSend("", "notifications.queue", event);
    }

}