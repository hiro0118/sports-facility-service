package com.hiro0118.tennisapi.domain.notificationconfig;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotificationConfigService {

    private final INotificationConfigRepository repository;

    public NotificationConfigService(INotificationConfigRepository repository) {
        this.repository = repository;
    }

    public NotificationConfigEntity getConfigurationById(String id) {
        var base = repository.getNotificationConfigBaseDataById(id);
        if (base != null) {
            var days = repository.getNotificationConfigDayListById(id);
            var times = repository.getNotificationConfigTimeListById(id);
            var parks = repository.getNotificationConfigParkListById(id);
            var dateExclusions = repository.getNotificationConfigDateExclusionListById(id);

            return NotificationConfigEntity.builder()
                .userId(id)
                .enabled(base.isEnabled())
                .dayList(days)
                .timeList(times)
                .parkList(parks)
                .dateExclusionList(dateExclusions)
                .build();
        } else {
            return NotificationConfigEntity.builder()
                .userId(id)
                .enabled(false)
                .dayList(new ArrayList<>())
                .timeList(new ArrayList<>())
                .parkList(new ArrayList<>())
                .dateExclusionList(new ArrayList<>())
                .build();
        }
    }

    public void registerConfigurationById(String id, NotificationConfigEntity input) {
//        repository.registerConfigurationById(id, input);
    }
}
