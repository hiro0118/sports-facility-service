package com.hiro0118.tennisapi.domain.park;

import lombok.Value;

@Value
public class ParkEntity {
    private final String id;
    private final String name;
    private final int postalCode;
    private final String address;
    private final int numOfCourts;
}
