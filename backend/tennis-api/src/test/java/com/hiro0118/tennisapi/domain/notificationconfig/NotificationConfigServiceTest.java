package com.hiro0118.tennisapi.domain.notificationconfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class NotificationConfigServiceTest {

    NotificationConfigService service;

    @Mock
    INotificationConfigRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new NotificationConfigService(repository);
    }

    @Test
    public void test() {

    }
}
