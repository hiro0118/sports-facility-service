package com.hiro0118.tennisapi.controllers.raffle;

import com.hiro0118.tennisapi.domain.raffle.RaffleService;
import com.hiro0118.tennisapi.domain.raffle.entities.RaffleStatusEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RaffleController {

    private final RaffleService service;

    public RaffleController(RaffleService service) {
        this.service = service;
    }

    @GetMapping(value = "/raffle-status")
    public List<RaffleStatusEntity> getRaffleStatus(
        @RequestParam(required = false) List<String> date,
        @RequestParam(required = false) List<String> time,
        @RequestParam(required = false) List<String> parkId
    ) {
        return service.getRaffleStatus(date, time, parkId);
    }
}
