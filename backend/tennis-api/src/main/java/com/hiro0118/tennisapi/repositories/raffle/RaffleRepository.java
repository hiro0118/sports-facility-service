package com.hiro0118.tennisapi.repositories.raffle;

import com.hiro0118.tennisapi.domain.raffle.entities.RaffleStatusEntity;
import com.hiro0118.tennisapi.domain.raffle.repositoryinterface.IRaffleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RaffleRepository implements IRaffleRepository {

    private final List<RaffleStatusEntity> dataList = List.of(
        new RaffleStatusEntity("2023-05-14", "1100-1300", "parkA", "park A", 5, 1),
        new RaffleStatusEntity("2023-05-14", "1300-1500", "parkA", "park A", 5, 1),
        new RaffleStatusEntity("2023-05-14", "1100-1300", "parkA", "park A", 5, 1),
        new RaffleStatusEntity("2023-05-14", "1300-1500", "parkB", "park B", 5, 1),
        new RaffleStatusEntity("2023-05-15", "1100-1300", "parkB", "park B", 5, 1),
        new RaffleStatusEntity("2023-05-15", "1300-1500", "parkC", "park C", 5, 1)
    );

    @Override
    public List<RaffleStatusEntity> getRaffleStatus(
        List<String> dateList,
        List<String> timeList,
        List<String> parkIdList
    ) {
       return  dataList.stream()
            .filter(data -> containedInFilter(dateList, data.getDate()))
            .filter(data -> containedInFilter(timeList, data.getTime()))
            .filter(data -> containedInFilter(parkIdList, data.getParkId()))
            .collect(Collectors.toList());
    }

    private boolean containedInFilter(List<String> filterList, String val) {
        if (filterList == null || filterList.isEmpty()) {
            return true;
        }
        for (String filter : filterList) {
            if (filter.equals(val)) {
                return true;
            }
        }
        return false;
    }
}
