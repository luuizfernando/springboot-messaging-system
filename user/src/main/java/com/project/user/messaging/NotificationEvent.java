package com.project.user.messaging;

public record NotificationEvent(Long userId, String text) {}