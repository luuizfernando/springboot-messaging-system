package com.project.notifications.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "notification")
@Data
public class Notification {

    @Id
    private String id;
    private Long userId;
    private String text;

}