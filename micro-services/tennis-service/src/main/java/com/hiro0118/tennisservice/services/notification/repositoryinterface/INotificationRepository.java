package com.hiro0118.tennisservice.services.notification.repositoryinterface;

import com.hiro0118.tennisservice.services.notification.NotificationConfigInput;
import com.hiro0118.tennisservice.services.notification.entities.NotificationConfigEntity;

import java.util.List;

public interface INotificationRepository {

    NotificationConfigEntity createConfiguration(NotificationConfigInput input);

    NotificationConfigEntity getConfiguration(String id);

    List<NotificationConfigEntity> getConfigurations();

    void updateConfiguration(NotificationConfigInput input);

    void updateConfigurations(List<NotificationConfigInput> inputList);

    void deleteConfiguration(String id);

    void deleteConfigurations(List<String> ids);
}
