package com.hiro0118.tennisapi.repositories.time;

import com.hiro0118.tennisapi.domain.time.TimeEntity;
import com.hiro0118.tennisapi.domain.time.ITimeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeRepository implements ITimeRepository {

    private final ITimeMapper mapper;

    public TimeRepository(ITimeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<TimeEntity> getTimes() {
        return mapper.getTimes();
    }
}
