package com.hiro0118.tennisapi.controllers.day;

import com.hiro0118.tennisapi.domain.day.DayEntity;
import com.hiro0118.tennisapi.domain.day.DayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DayController {

    private final DayService service;

    public DayController(DayService service) {
        this.service = service;
    }

    @GetMapping(value = "/days")
    public List<DayEntity> getDays() {
        return service.getDays();
    }
}
