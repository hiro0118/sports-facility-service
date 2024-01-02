package com.hiro0118.tennisapi.domain.raffle;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RaffleStatusResponse {
    private final String date;
    private final String time;
    private final String parkId;
    private final String parkName;
    private final int numOfCourts;
    private final int numOfApplications;
    private final Double ratio;
}
