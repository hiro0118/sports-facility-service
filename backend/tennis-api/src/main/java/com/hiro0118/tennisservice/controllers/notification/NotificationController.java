package com.hiro0118.tennisservice.controllers.notification;

import com.hiro0118.tennisservice.services.notification.NotificationConfigInput;
import com.hiro0118.tennisservice.services.notification.NotificationService;
import com.hiro0118.tennisservice.services.notification.entities.NotificationConfigEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
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
