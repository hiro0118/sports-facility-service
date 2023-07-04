package com.hiro0118.tennisapi.controllers.date;

import com.hiro0118.tennisapi.domain.date.DateService;
import com.hiro0118.tennisapi.domain.date.enities.DateEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DateController {

    private final DateService service;

    public DateController(DateService service) {
        this.service = service;
    }

    @GetMapping(value = "/dates")
    public List<DateEntity> getDates(
        @RequestParam(required = false, defaultValue = "true") boolean weekendOnly
    ) {
        return service.getDates(weekendOnly);
    }
}
