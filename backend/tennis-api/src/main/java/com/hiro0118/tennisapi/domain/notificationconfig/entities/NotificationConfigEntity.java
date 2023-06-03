package com.hiro0118.tennisapi.domain.notificationconfig.entities;

import lombok.Value;

import java.util.List;

@Value
public class NotificationConfigEntity {
    private final String id;
    private final List<String> parkIdList;
}
