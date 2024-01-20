package com.hiro0118.tennisapi.domain.notificationconfig;

import com.hiro0118.tennisapi.domain.park.ParkService;
import com.hiro0118.tennisapi.domain.time.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class NotificationConfigServiceTest {

    NotificationConfigService service;

    @Mock
    INotificationConfigRepository repository;

    @Mock
    TimeService timeService;

    @Mock
    ParkService parkService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new NotificationConfigService(repository, timeService, parkService);
    }

    @Test
    public void test() {

    }
}
