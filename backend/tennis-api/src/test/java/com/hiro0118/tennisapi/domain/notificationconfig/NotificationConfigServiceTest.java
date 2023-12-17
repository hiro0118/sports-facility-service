package com.hiro0118.tennisapi.domain.notificationconfig;

import com.hiro0118.tennisapi.domain.notificationconfig.entities.NotificationConfigEntity;
import com.hiro0118.tennisapi.domain.notificationconfig.repositoryinterface.INotificationConfigRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {NotificationConfigService.class, INotificationConfigRepository.class})
class NotificationConfigServiceTest {

    @Autowired
    NotificationConfigService service;

    @MockBean
    INotificationConfigRepository repository;

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