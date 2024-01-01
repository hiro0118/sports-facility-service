package com.hiro0118.tennisapi.domain.notificationconfig;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationConfigService {

    private final INotificationConfigRepository repository;

    public NotificationConfigService(INotificationConfigRepository repository) {
        this.repository = repository;
    }

    public NotificationConfigEntity createConfiguration(NotificationConfigInput inputData) {
        return repository.createConfiguration(inputData);
    }

    public NotificationConfigEntity getConfiguration(String id) {
        return repository.getConfiguration(id);
    }

    public List<NotificationConfigEntity> getConfigurations() {
        return repository.getConfigurations();
    }
}
