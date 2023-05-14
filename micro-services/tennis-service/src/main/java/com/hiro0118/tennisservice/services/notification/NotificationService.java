package com.hiro0118.tennisservice.services.notification;

import com.hiro0118.tennisservice.services.notification.entities.NotificationConfigEntity;
import com.hiro0118.tennisservice.services.notification.repositoryinterface.INotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final INotificationRepository repository;

    public NotificationService(INotificationRepository repository) {
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
