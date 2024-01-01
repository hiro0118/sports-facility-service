package com.hiro0118.tennisapi.domain.notificationconfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        var expectedEntity1 = new NotificationConfigEntity("id1", List.of("data1", "data2"));
        var expectedEntity2 = new NotificationConfigEntity("id2", List.of("data1", "data2", "data3"));
        when(repository.getConfigurations()).thenReturn(
            List.of(expectedEntity1, expectedEntity2)
        );

        var result = service.getConfigurations();

        assertEquals(expectedEntity1, result.get(0));
        assertEquals(expectedEntity2, result.get(1));
    }
}
