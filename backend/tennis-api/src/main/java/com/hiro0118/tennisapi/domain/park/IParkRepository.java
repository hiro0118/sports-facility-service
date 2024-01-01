package com.hiro0118.tennisapi.domain.park;

import java.util.List;

public interface IParkRepository {
    List<ParkEntity> getParks();
}
