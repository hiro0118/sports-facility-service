package com.hiro0118.tennisapi.domain.park.repositoryinterface;

import com.hiro0118.tennisapi.domain.park.entities.ParkEntity;

import java.util.List;

public interface IParkRepository {
    List<ParkEntity> getParks();
}
