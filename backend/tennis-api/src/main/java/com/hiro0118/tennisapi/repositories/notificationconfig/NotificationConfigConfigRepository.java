package com.hiro0118.tennisapi.repositories.notificationconfig;

import com.hiro0118.tennisapi.domain.notificationconfig.NotificationConfigInput;
import com.hiro0118.tennisapi.domain.notificationconfig.INotificationConfigRepository;
import com.hiro0118.tennisapi.domain.notificationconfig.NotificationConfigEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
public class NotificationConfigConfigRepository implements INotificationConfigRepository {

    private final Map<String, NotificationConfigEntity> entityDataMap = new HashMap<>();
    private int nextId = 0;

    private final ReentrantReadWriteLock repositoryLock = new ReentrantReadWriteLock();
    private final Lock readLock = repositoryLock.readLock();
    private final Lock writeLock = repositoryLock.writeLock();

    private String generateNextId() {
        String newId = String.valueOf(nextId++);
        return newId;
    }

    @Override
    public NotificationConfigEntity createConfiguration(NotificationConfigInput input) {
        NotificationConfigEntity newEntity = null;
        writeLock.lock();
        try {
            String newId = generateNextId();
            newEntity = new NotificationConfigEntity(newId, input.getParkIdList());
            entityDataMap.put(newId, newEntity);
        } finally {
            writeLock.unlock();
        }
        return newEntity;
    }


    @Override
    public List<NotificationConfigEntity> getConfigurations() {
        readLock.lock();
        try {
            return entityDataMap.values().stream().toList();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public NotificationConfigEntity getConfiguration(String id) {
        readLock.lock();
        try {
            return entityDataMap.get(id);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void updateConfiguration(NotificationConfigInput input) {

    }

    @Override
    public void updateConfigurations(List<NotificationConfigInput> inputList) {

    }

    @Override
    public void deleteConfiguration(String id) {

    }

    @Override
    public void deleteConfigurations(List<String> ids) {

    }
}
