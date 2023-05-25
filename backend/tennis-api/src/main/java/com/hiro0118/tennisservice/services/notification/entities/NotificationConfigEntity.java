package com.hiro0118.tennisservice.services.notification.entities;

import lombok.Value;

import java.util.List;

@Value
public class NotificationConfigEntity {
    private final String id;
    private final List<String> parkIdList;
}
