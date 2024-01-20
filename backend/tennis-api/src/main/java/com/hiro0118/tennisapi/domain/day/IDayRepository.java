package com.hiro0118.tennisapi.domain.day;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDayRepository {
    List<DayEntity> getDays();
}
