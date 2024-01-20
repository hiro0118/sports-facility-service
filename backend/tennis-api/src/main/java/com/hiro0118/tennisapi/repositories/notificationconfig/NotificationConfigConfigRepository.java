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
    public void insertNotificationConfigBaseDateById(String id, NotificationConfigBaseData input) {
        mapper.insertNotificationConfigBaseDataById(id, input.isEnabled());
    }

    @Override
    public void deleteNotificationConfigBaseDateById(String id) {
        mapper.deleteNotificationConfigBaseDataById(id);
    }

    @Override
    public List<String> getNotificationConfigDayListById(String id) {
        return mapper.getNotificationConfigDayListById(id);
    }

    @Override
    public void insertNotificationConfigDayListById(String id, String day) {
        mapper.insertNotificationConfigDayListById(id, day);
    }

    @Override
    public void deleteNotificationConfigDayListById(String id) {
        mapper.deleteNotificationConfigDayListById(id);
    }

    @Override
    public List<String> getNotificationConfigTimeListById(String id) {
        return mapper.getNotificationConfigTimeListById(id);
    }

    @Override
    public void insertNotificationConfigTimeListById(String id, String time) {
        mapper.insertNotificationConfigTimeListById(id, time);
    }

    @Override
    public void deleteNotificationConfigTimeListById(String id) {
        mapper.deleteNotificationConfigTimeListById(id);
    }

    @Override
    public List<String> getNotificationConfigParkListById(String id) {
        return mapper.getNotificationConfigParkListById(id);
    }

    @Override
    public void insertNotificationConfigParkListById(String id, String park) {
        mapper.insertNotificationConfigParkListById(id, park);
    }

    @Override
    public void deleteNotificationConfigParkListById(String id) {
        mapper.deleteNotificationConfigParkListById(id);
    }

    @Override
    public List<DateEntity> getNotificationConfigDateExclusionListById(String id) {
        return mapper.getNotificationConfigDateExclusionListById(id);
    }

    @Override
    public void insertNotificationConfigDateExclusionListById(String id, DateEntity date) {
        mapper.insertNotificationConfigDateExclusionListById(id, date);
    }

    @Override
    public void deleteNotificationConfigDateExclusionListById(String id) {
        mapper.deleteNotificationConfigDateExclusionListById(id);
    }
}
