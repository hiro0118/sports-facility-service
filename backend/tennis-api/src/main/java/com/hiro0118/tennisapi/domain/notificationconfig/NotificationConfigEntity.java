package com.hiro0118.tennisapi.domain.notificationconfig;

import com.hiro0118.tennisapi.domain.date.DateEntity;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class NotificationConfigEntity {
    private final String userId;
    private final boolean enabled;
    private final List<String> dayList;
    private final List<String> timeList;
    private final List<String> parkList;
    private final List<DateEntity> dateExclusionList;
}
