package com.hiro0118.tennisservice.repositories.park;

import com.hiro0118.tennisservice.domain.park.entities.ParkEntity;
import com.hiro0118.tennisservice.domain.park.repositoryinterface.IParkRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkRepository implements IParkRepository {

    private final List<ParkEntity> dataList = List.of(
        new ParkEntity("1", "parkA", "111-1111", "addressA", 1),
        new ParkEntity("2", "parkB", "111-1111", "addressB", 1),
        new ParkEntity("3", "parkC", "111-1111", "addressC", 1),
        new ParkEntity("4", "parkD", "111-1111", "addressD", 1),
        new ParkEntity("5", "parkE", "111-1111", "addressE", 1)
    );

    public List<ParkEntity> getParks() {
        return dataList;
    }
}
