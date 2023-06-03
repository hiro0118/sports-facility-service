package com.hiro0118.tennisapi.domain.notificationconfig;

import lombok.Value;

import java.util.List;

@Value
public class NotificationConfigInput {
    private String id;
    private List<String> parkIdList;
}
