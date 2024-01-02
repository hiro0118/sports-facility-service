package com.hiro0118.tennisapi.domain.raffle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RaffleServiceTest {

    RaffleService service;

    @Mock
    IRaffleRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new RaffleService(repository);
    }

    @Test
    void givenStatus_whenGetRaffleStatus_ThenReturnCorrectRatio() {
        var entity = new RaffleStatusEntity("20240101", "0700-0900", "1", "Park A", 3, 5);
        when(repository.getRaffleStatus(any(), any(), any())).thenReturn(List.of(entity));

        var responseList = service.getRaffleStatus(List.of("20240101"), List.of("0700-0900"), List.of("A"));

        assertEquals(1, responseList.size());
        var result = responseList.get(0);
        assertOriginalValues(entity, result);
        assertEquals(1.67, result.getRatio());
    }

    @Test
    void givenZeroCourts_whenGetRaffleStatus_ThenReturnNullRatio() {
        var entity = new RaffleStatusEntity("20240101", "0700-0900", "1", "Park A", 0, 5);
        when(repository.getRaffleStatus(any(), any(), any())).thenReturn(List.of(entity));

        var responseList = service.getRaffleStatus(List.of("20240101"), List.of("0700-0900"), List.of("A"));

        assertEquals(1, responseList.size());
        var result = responseList.get(0);
        assertOriginalValues(entity, result);
        assertNull(result.getRatio());
    }

    private void assertOriginalValues(RaffleStatusEntity expected, RaffleStatusResponse result) {
        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getTime(), result.getTime());
        assertEquals(expected.getParkId(), result.getParkId());
        assertEquals(expected.getParkName(), result.getParkName());
    }
}
