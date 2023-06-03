package com.hiro0118.tennisservice.domain.notificationconfig;

import com.hiro0118.tennisservice.domain.notificationconfig.entities.NotificationConfigEntity;
import com.hiro0118.tennisservice.domain.notificationconfig.repositoryinterface.INotificationConfigRepository;
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
