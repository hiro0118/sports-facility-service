package com.hiro0118.tennisapi.domain.day;

import lombok.Value;

@Value
public class DayEntity {
    private final String id;
    private final String name;
    private final String shortName;
}
