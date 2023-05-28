package com.hiro0118.tennisservice.domain.raffle.entities;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RaffleStatusEntity {
    private final String date;
    private final String time;
    private final String parkId;
    private final String parkName;
    private final int numOfCourts;
    private final int numOfApplications;
}
