package com.hiro0118.tennisapi.domain.park.entities;

import lombok.Value;

@Value
public class ParkEntity {
    private final String parkId;
    private final String parkName;
    private final String postalCode;
    private final String address;
    private final int numOfCourts;
}
