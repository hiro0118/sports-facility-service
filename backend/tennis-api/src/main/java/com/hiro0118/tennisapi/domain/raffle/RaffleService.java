package com.hiro0118.tennisservice.domain.raffle;

import com.hiro0118.tennisservice.domain.raffle.entities.RaffleStatusEntity;
import com.hiro0118.tennisservice.domain.raffle.repositoryinterface.IRaffleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaffleService {

    private IRaffleRepository repository;

    private static final List<String> EMPTY_STR_LIST = List.of();

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
