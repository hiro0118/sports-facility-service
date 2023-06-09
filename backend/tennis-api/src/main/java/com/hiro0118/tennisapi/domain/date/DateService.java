package com.hiro0118.tennisapi.domain.date;

import com.hiro0118.tennisapi.domain.date.enities.DateEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DateService {

    private final static int RAFFLE_DEADLINE = 10;

    public List<DateEntity> getDates(boolean weekendOnly) {

        //TODO: Get this from DB.
        LocalDateTime time = LocalDateTime.now();
        int year = time.getYear();
        int month = time.getMonthValue() + 1;

        // Get the first day of the specified month
        LocalDate date = LocalDate.of(year, month, 1);

        // Iterate through all the days of the month
        List<DateEntity> resultDates = new ArrayList<>();
        while (date.getMonthValue() == month) {
            if (!weekendOnly || isWeekend(date)) {
                resultDates.add(new DateEntity(year, month, date.getDayOfMonth()));
            }
            date = date.plusDays(1);
        }
        return resultDates;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
