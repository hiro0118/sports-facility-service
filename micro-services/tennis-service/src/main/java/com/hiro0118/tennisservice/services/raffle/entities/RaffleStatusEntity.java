package com.hiro0118.tennisservice.services.raffle.entities;

import lombok.Value;

@Value
public class RaffleStatusEntity {
    private final String date;
    private final String time;
    private final String parkId;
}
