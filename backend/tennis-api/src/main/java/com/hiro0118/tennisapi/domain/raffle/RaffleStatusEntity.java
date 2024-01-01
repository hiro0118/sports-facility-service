package com.hiro0118.tennisapi.domain.raffle;

import lombok.Value;

@Value
public class RaffleStatusEntity {
    private final String date;
    private final String time;
    private final String parkId;
    private final int numOfApplications;
}