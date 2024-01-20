package com.hiro0118.tennisapi.domain.notificationconfig;

import com.hiro0118.tennisapi.domain.notificationconfig.data.NotificationConfigBaseData;
import com.hiro0118.tennisapi.domain.park.ParkEntity;
import com.hiro0118.tennisapi.domain.park.ParkService;
import com.hiro0118.tennisapi.domain.time.TimeEntity;
import com.hiro0118.tennisapi.domain.time.TimeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class NotificationConfigService {

    private final INotificationConfigRepository repository;
    private final TimeService timeService;
    private final ParkService parkService;

    public NotificationConfigService(INotificationConfigRepository repository, TimeService timeService,
        ParkService parkService) {
        this.repository = repository;
        this.timeService = timeService;
        this.parkService = parkService;
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

    public void registerConfigurationById(String userId, NotificationConfigEntity input) {
        // Run validations
        var timeIdSet = timeService.getTimes().stream().map(TimeEntity::getTime).collect(Collectors.toSet());
        if (!timeIdSet.containsAll(input.getTimeList())) {
            throw new IllegalArgumentException("Illegal time input!");
        }

        var parkIdSet = parkService.getParks().stream().map(ParkEntity::getId).collect(Collectors.toSet());
        if (!parkIdSet.containsAll(input.getParkList())) {
            throw new IllegalArgumentException("Illegal park input!");
        }

        // Delete existing data
        repository.deleteNotificationConfigBaseDateById(userId);
        repository.deleteNotificationConfigDayListById(userId);
        repository.deleteNotificationConfigTimeListById(userId);
        repository.deleteNotificationConfigParkListById(userId);
        repository.deleteNotificationConfigDateExclusionListById(userId);

        // Insert new data
        var base = new NotificationConfigBaseData(userId, input.isEnabled());
        repository.insertNotificationConfigBaseDateById(userId, base);
        for (var dayInput : input.getDayList()) {
            repository.insertNotificationConfigDayListById(userId, dayInput);
        }
        for (var timeInput : input.getTimeList()) {
            repository.insertNotificationConfigTimeListById(userId, timeInput);
        }
        for (var parkInput : input.getParkList()) {
            repository.insertNotificationConfigParkListById(userId, parkInput);
        }
        for (var dateInput : input.getDateExclusionList()) {
            repository.insertNotificationConfigDateExclusionListById(userId, dateInput);
        }
    }
}
