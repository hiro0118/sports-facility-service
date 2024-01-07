package com.hiro0118.tennisapi.domain.notificationconfig;

import com.hiro0118.tennisapi.domain.date.DateEntity;
import com.hiro0118.tennisapi.domain.notificationconfig.data.*;

import java.util.List;

public interface INotificationConfigRepository {

    // Base Info
    NotificationConfigBaseData getNotificationConfigBaseDataById(String id);

    void insertNotificationConfigBaseDateById(String id, NotificationConfigBaseData input);

    void deleteNotificationConfigBaseDateById(String id);

    // Days
    List<String> getNotificationConfigDayListById(String id);

    void insertNotificationConfigDayListById(String id, String day);

    void deleteNotificationConfigDayListById(String id);

    // Times
    List<String> getNotificationConfigTimeListById(String id);

    void insertNotificationConfigTimeListById(String id, String time);

    void deleteNotificationConfigTimeListById(String id);

    // Parks
    List<String> getNotificationConfigParkListById(String id);

    void insertNotificationConfigParkListById(String id, String park);

    void deleteNotificationConfigParkListById(String id);

    // Date Exclusions
    List<DateEntity> getNotificationConfigDateExclusionListById(String id);

    void insertNotificationConfigDateExclusionListById(String id, DateEntity date);

    void deleteNotificationConfigDateExclusionListById(String id);
}
