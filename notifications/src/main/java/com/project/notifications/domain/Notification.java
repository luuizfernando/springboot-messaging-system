package com.project.notifications.domain;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "notification")
@Data
public class Notification {

    private UUID uuid;
    private String text;

}