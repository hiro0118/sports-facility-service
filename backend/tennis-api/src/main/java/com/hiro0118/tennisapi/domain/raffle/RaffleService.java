package com.hiro0118.tennisapi.domain.raffle;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaffleService {

    private IRaffleRepository repository;

    public RaffleService(IRaffleRepository repository) {
        this.repository = repository;
    }

    public List<RaffleStatusResponse> getRaffleStatus(
        List<String> dateList,
        List<String> timeList,
        List<String> parkIdList
    ) {
        var raffleStatusEntities = repository.getRaffleStatus(dateList, timeList, parkIdList);
        return raffleStatusEntities.stream().map(this::convert).toList();
    }

    private RaffleStatusResponse convert(RaffleStatusEntity entity) {
        Double ratio = null;
        if (entity.getNumOfCourts() != 0) {
            ratio = (double) entity.getNumOfApplications() / entity.getNumOfCourts();
            ratio = Math.round( ratio * 100.0 ) / 100.0;
        }
        return RaffleStatusResponse
            .builder()
            .date(entity.getDate())
            .time(entity.getTime())
            .parkId(entity.getParkId())
            .parkName(entity.getParkName())
            .numOfCourts(entity.getNumOfCourts())
            .numOfApplications(entity.getNumOfApplications())
            .numOfApplications(entity.getNumOfApplications())
            .ratio(ratio)
            .build();
    }
}
