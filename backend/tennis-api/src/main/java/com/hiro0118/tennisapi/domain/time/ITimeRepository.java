package com.hiro0118.tennisapi.domain.time;

import com.hiro0118.tennisapi.domain.time.TimeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITimeRepository {

    List<TimeEntity> getTimes();
}
