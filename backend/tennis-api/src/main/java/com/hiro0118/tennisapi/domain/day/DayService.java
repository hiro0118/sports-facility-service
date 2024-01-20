package com.hiro0118.tennisapi.domain.day;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayService {

    private final IDayRepository repository;

    public DayService(IDayRepository repository) {
        this.repository = repository;
    }

    public List<DayEntity> getDays() {
        return repository.getDays();
    }
}
