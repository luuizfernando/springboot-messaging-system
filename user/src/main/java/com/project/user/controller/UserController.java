package com.project.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.user.domain.user.User;
import com.project.user.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;
    
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User u) {
        User user = service.create(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("{id}/messages")
    public ResponseEntity<Void> sendMessage(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String payload = body.get("payload");
        service.sendNotification(id, payload);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}