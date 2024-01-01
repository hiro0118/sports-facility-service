package com.hiro0118.tennisapi.domain.time;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    private ITimeRepository repository;

    public TimeService(ITimeRepository repository) {
        this.repository = repository;
    }

    public List<TimeEntity> getTimes() {
        return this.repository.getTimes();
    }
}
