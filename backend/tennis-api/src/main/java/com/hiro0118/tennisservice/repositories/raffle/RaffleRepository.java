package com.hiro0118.tennisservice.repositories.raffle;

import com.hiro0118.tennisservice.services.raffle.entities.RaffleStatusEntity;
import com.hiro0118.tennisservice.services.raffle.repositoryinterface.IRaffleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RaffleRepository implements IRaffleRepository {

    private final List<RaffleStatusEntity> dataList = List.of(
        new RaffleStatusEntity("2023-05-14", "1100-1300", "parkA"),
        new RaffleStatusEntity("2023-05-14", "1300-1500", "parkA"),
        new RaffleStatusEntity("2023-05-14", "1100-1300", "parkA"),
        new RaffleStatusEntity("2023-05-14", "1300-1500", "parkB"),
        new RaffleStatusEntity("2023-05-15", "1100-1300", "parkC"),
        new RaffleStatusEntity("2023-05-15", "1300-1500", "parkC")
    );

    @Override
    public List<RaffleStatusEntity> getRaffleStatus(
        List<String> dateList,
        List<String> timeList,
        List<String> parkIdList
    ) {
       return  dataList.stream()
            .filter(d -> containedInFilter(dateList, d.getDate()))
            .filter(d -> containedInFilter(timeList, d.getDate()))
            .filter(d -> containedInFilter(parkIdList, d.getDate()))
            .collect(Collectors.toList());
    }

    private boolean containedInFilter(List<String> filterList, String val) {
        return filterList == null || filterList.contains(val);
    }
}
