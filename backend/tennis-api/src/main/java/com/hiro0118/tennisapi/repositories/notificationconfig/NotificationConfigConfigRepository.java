package com.hiro0118.tennisapi.repositories.notificationconfig;

import com.hiro0118.tennisapi.domain.date.DateEntity;
import com.hiro0118.tennisapi.domain.notificationconfig.INotificationConfigRepository;
import com.hiro0118.tennisapi.domain.notificationconfig.data.NotificationConfigBaseData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationConfigConfigRepository implements INotificationConfigRepository {

    private final INotificationConfigMapper mapper;

    public NotificationConfigConfigRepository(INotificationConfigMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public NotificationConfigBaseData getNotificationConfigBaseDataById(String id) {
        return mapper.getNotificationConfigBaseDataById(id);
    }

    @Override
    public List<String> getNotificationConfigDayListById(String id) {
        return mapper.getNotificationConfigDayListById(id);
    }

    @Override
    public List<String> getNotificationConfigTimeListById(String id) {
        return mapper.getNotificationConfigTimeListById(id);
    }

    @Override
    public List<String> getNotificationConfigParkListById(String id) {
        return mapper.getNotificationConfigParkListById(id);
    }

    @Override
    public List<DateEntity> getNotificationConfigDateExclusionListById(String id) {
        return mapper.getNotificationConfigDateExclusionListById(id);
    }
}
