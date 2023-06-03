package com.hiro0118.tennisservice.domain.raffle.repositoryinterface;

import com.hiro0118.tennisservice.domain.raffle.entities.RaffleStatusEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRaffleRepository {

     List<RaffleStatusEntity> getRaffleStatus(
        List<String> dateList,
        List<String> timeList,
        List<String> parkIdList
    );
}
