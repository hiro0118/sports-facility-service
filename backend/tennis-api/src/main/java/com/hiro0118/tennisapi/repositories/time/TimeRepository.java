package com.hiro0118.tennisapi.repositories.time;

import com.hiro0118.tennisapi.domain.time.entities.TimeEntity;
import com.hiro0118.tennisapi.domain.time.repositoryinterface.ITimeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeRepository implements ITimeRepository {

    private final List<TimeEntity> timeList = List.of(
        new TimeEntity("1", "0900-1100"),
        new TimeEntity("2", "1100-1300"),
        new TimeEntity("3", "1300-1500"),
        new TimeEntity("4", "1500-1700"),
        new TimeEntity("5", "1700-1900"),
        new TimeEntity("6", "1900-2100")
    );

    public List<TimeEntity> getTimes() {
        return timeList;
    }
}
