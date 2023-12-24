package com.hiro0118.tennisapi.repositories.time;

import com.hiro0118.tennisapi.domain.time.entities.TimeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITimeMapper {

    List<TimeEntity> getTimes();
}
