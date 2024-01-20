package com.hiro0118.tennisapi.controllers.notificationconfig;

import com.hiro0118.tennisapi.domain.notificationconfig.NotificationConfigService;
import com.hiro0118.tennisapi.domain.notificationconfig.NotificationConfigEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationConfigController {

    private final NotificationConfigService service;

    public NotificationConfigController(NotificationConfigService service) {
        this.service = service;
    }

    @GetMapping("/notification-configs/{id}")
    public NotificationConfigEntity getConfiguration(@PathVariable String id) {
        return service.getConfigurationById(id);
    }

    @PutMapping("/notification-configs/{id}")
    public void putConfiguration(@PathVariable String id, @RequestBody NotificationConfigEntity input) {
        service.registerConfigurationById(id, input);
    }
 }
