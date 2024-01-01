package com.hiro0118.tennisapi.domain.raffle;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaffleService {

    private IRaffleRepository repository;

    public RaffleService(IRaffleRepository repository) {
        this.repository = repository;
    }

    public List<RaffleStatusEntity> getRaffleStatus(
        List<String> dateList,
        List<String> timeList,
        List<String> parkIdList
    ) {
        return this.repository.getRaffleStatus(dateList, timeList, parkIdList);
    }
}
