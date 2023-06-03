package com.hiro0118.tennisapi.domain.notificationconfig.repositoryinterface;

import com.hiro0118.tennisapi.domain.notificationconfig.NotificationConfigInput;
import com.hiro0118.tennisapi.domain.notificationconfig.entities.NotificationConfigEntity;

import java.util.List;

public interface INotificationConfigRepository {

    NotificationConfigEntity createConfiguration(NotificationConfigInput input);

    NotificationConfigEntity getConfiguration(String id);

    List<NotificationConfigEntity> getConfigurations();

    void updateConfiguration(NotificationConfigInput input);

    void updateConfigurations(List<NotificationConfigInput> inputList);

    void deleteConfiguration(String id);

    void deleteConfigurations(List<String> ids);
}
