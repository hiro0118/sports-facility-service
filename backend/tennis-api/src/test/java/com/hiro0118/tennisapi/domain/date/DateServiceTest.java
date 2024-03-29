package com.hiro0118.tennisapi.domain.date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateServiceTest {

    DateService service;

    @BeforeEach
    void setup() {
        service = new DateService();
    }

    @Test
    public void getDates_weekendOnlyTrue_returnWeekends() {
        var resultDates = service.getDates(2023, 12, true);

        int[] expectedDates = {2, 3, 9, 10, 16, 17, 23, 24, 30, 31};
        assertEquals(expectedDates.length, resultDates.size());
        for (int i = 0; i < expectedDates.length; i++) {
            assertDate(2023, 12,expectedDates[i], resultDates.get(i));
        }
    }

    @Test
    public void getDates_weekendOnlyFalse_returnAllDates() {
        var result = service.getDates(2023, 12, false);

        final int expectedSize = 31;
        assertEquals(expectedSize, result.size());
        for (int i = 0; i < expectedSize; i++) {
            assertDate(2023, 12, i + 1, result.get(i));
        }
    }

    private void assertDate(int expectedYear, int expectedMonth, int expectedDate, DateEntity result) {
        assertEquals(expectedYear, result.getYear(), result.toString());
        assertEquals(expectedMonth, result.getMonth(), result.toString());
        assertEquals(expectedDate, result.getDate(), result.toString());
    }
}