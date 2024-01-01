package com.hiro0118.tennisapi.repositories.raffle;

import com.hiro0118.tennisapi.domain.raffle.entities.RaffleStatusEntity;
import com.hiro0118.tennisapi.domain.raffle.repositoryinterface.IRaffleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RaffleRepository implements IRaffleRepository {

    private final IRaffleMapper mapper;

    public RaffleRepository(IRaffleMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<RaffleStatusEntity> getRaffleStatus(
        List<String> dateList,
        List<String> timeList,
        List<String> parkIdList
    ) {
        return mapper.getRaffleStatus(dateList, timeList, parkIdList);
    }
}
