package com.hiro0118.tennisapi.domain.time.repositoryinterface;

import com.hiro0118.tennisapi.domain.time.entities.TimeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITimeRepository {

    List<TimeEntity> getTimes();
}
