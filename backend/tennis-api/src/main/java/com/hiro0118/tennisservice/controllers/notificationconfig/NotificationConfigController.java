package com.hiro0118.tennisservice.controllers.notificationconfig;

import com.hiro0118.tennisservice.domain.notificationconfig.NotificationConfigInput;
import com.hiro0118.tennisservice.domain.notificationconfig.NotificationConfigService;
import com.hiro0118.tennisservice.domain.notificationconfig.entities.NotificationConfigEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class NotificationConfigController {

    private final NotificationConfigService service;

    public NotificationConfigController(NotificationConfigService service) {
        this.service = service;
    }

    @PostMapping(path="/notification-configs")
    public NotificationConfigEntity createConfiguration(@RequestBody NotificationConfigInput inputData) {
        return service.createConfiguration(inputData);
    }

    @GetMapping(path="/notification-configs")
    public List<NotificationConfigEntity> getConfigurations() {
        return service.getConfigurations();
    }

    @GetMapping("/notification-configs/{id}")
    public NotificationConfigEntity getConfiguration(@PathVariable String id) {
        return service.getConfiguration(id);
    }
}
