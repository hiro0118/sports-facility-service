package com.hiro0118.tennisservice.controllers.raffle;

import com.hiro0118.tennisservice.services.raffle.RaffleService;
import com.hiro0118.tennisservice.services.raffle.entities.RaffleStatusEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RaffleController {

    public final RaffleService service;

    public RaffleController(RaffleService service) {
        this.service = service;
    }

    @GetMapping(value = "/raffle-status")
    public List<RaffleStatusEntity> getRaffleStatus(
        @RequestParam(required = false) List<String> dateList,
        @RequestParam(required = false) List<String> timeList,
        @RequestParam(required = false) List<String> parkIdList
    ) {
        return service.getRaffleStatus(dateList, timeList, parkIdList);
    }
}
