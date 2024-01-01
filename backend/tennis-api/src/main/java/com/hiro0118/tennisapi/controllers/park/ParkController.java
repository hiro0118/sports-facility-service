package com.hiro0118.tennisapi.controllers.park;

import com.hiro0118.tennisapi.domain.park.ParkService;
import com.hiro0118.tennisapi.domain.park.ParkEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParkController {

    private final ParkService service;

    public ParkController(ParkService service) {
        this.service = service;
    }

    @GetMapping(value = "/parks")
    public List<ParkEntity> getParks() {
        return service.getParks();
    }
}
