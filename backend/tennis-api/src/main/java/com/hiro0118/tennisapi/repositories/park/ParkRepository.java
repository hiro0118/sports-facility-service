package com.hiro0118.tennisapi.repositories.park;

import com.hiro0118.tennisapi.domain.park.entities.ParkEntity;
import com.hiro0118.tennisapi.domain.park.repositoryinterface.IParkRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkRepository implements IParkRepository {

    private final IParkMapper mapper;

    public ParkRepository(IParkMapper mapper) {
        this.mapper = mapper;
    }

    public List<ParkEntity> getParks() {
        return mapper.getParks();
    }
}
