package com.hiro0118.tennisapi.repositories.day;

import com.hiro0118.tennisapi.domain.day.DayEntity;
import com.hiro0118.tennisapi.domain.day.IDayRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DayRepository implements IDayRepository {

    private final IDayMapper mapper;

    public DayRepository(IDayMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<DayEntity> getDays() {
        return mapper.getDays();
    }
}
