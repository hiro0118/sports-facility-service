package com.hiro0118.tennisapi.repositories.time;

import com.hiro0118.tennisapi.domain.time.entities.TimeEntity;
import com.hiro0118.tennisapi.domain.time.repositoryinterface.ITimeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeRepository implements ITimeRepository {

    private final ITimeMapper mapper;

    public TimeRepository(ITimeMapper mapper) {
        this.mapper = mapper;
    }

    public List<TimeEntity> getTimes() {
        return mapper.getTimes();
    }
}
