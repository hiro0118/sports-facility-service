package com.hiro0118.tennisapi.domain.notificationconfig.data;

import lombok.Value;

import java.util.List;

@Value
public class NotificationConfigBaseData {
    private final String userId;
    private final boolean enabled;
}
