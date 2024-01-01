package com.hiro0118.tennisapi.domain.time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TimeServiceTest {

    TimeService service;

    @Mock
    ITimeRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new TimeService(repository);
    }

    @Test
    void test() {
        List<TimeEntity> expected = List.of(new TimeEntity("0900-1100"));
        when(repository.getTimes()).thenReturn(expected);

        var result = service.getTimes();

        assertListEquals(expected, result);
    }

    private void assertListEquals(List<TimeEntity> expected, List<TimeEntity> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
