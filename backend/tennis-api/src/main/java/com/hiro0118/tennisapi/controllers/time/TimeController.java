package com.hiro0118.tennisapi.controllers.time;

import com.hiro0118.tennisapi.domain.time.TimeService;
import com.hiro0118.tennisapi.domain.time.entities.TimeEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeController {

    private final TimeService service;

    public TimeController(TimeService service) {
        this.service = service;
    }

    @GetMapping(value = "/times")
    public List<TimeEntity> getTimes() {
        return service.getTimes();
    }
}
