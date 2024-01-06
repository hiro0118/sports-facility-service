package com.hiro0118.tennisapi.domain.notificationconfig;

import com.hiro0118.tennisapi.domain.date.DateEntity;
import com.hiro0118.tennisapi.domain.notificationconfig.data.*;

import java.util.List;

public interface INotificationConfigRepository {

    NotificationConfigBaseData getNotificationConfigBaseDataById(String id);

    List<String> getNotificationConfigDayListById(String id);

    List<String> getNotificationConfigTimeListById(String id);

    List<String> getNotificationConfigParkListById(String id);

    List<DateEntity> getNotificationConfigDateExclusionListById(String id);
}
