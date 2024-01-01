package com.hiro0118.tennisapi.repositories.raffle;

import com.hiro0118.tennisapi.domain.raffle.RaffleStatusEntity;
import com.hiro0118.tennisapi.domain.raffle.IRaffleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@ActiveProfiles("test")
@Transactional
@Sql(statements = {
    "INSERT INTO time (time) VALUES ('0700-0900');",
    "INSERT INTO time (time) VALUES ('0900-1100');",
    "INSERT INTO time (time) VALUES ('1100-1300');",
    "INSERT INTO park (id, name, postal_code, address, num_of_courts) VALUES ('A', 'Park A', 1111111, 'address A', 1);",
    "INSERT INTO park (id, name, postal_code, address, num_of_courts) VALUES ('B', 'Park B', 2222222, 'address B', 2);",
    "INSERT INTO park (id, name, postal_code, address, num_of_courts) VALUES ('C', 'Park C', 3333333, 'address C', 3);",
    "INSERT INTO raffle_status (date, time, park_id, num_of_applications) VALUES ('20231230', '0700-0900', 'A', 1);",
    "INSERT INTO raffle_status (date, time, park_id, num_of_applications) VALUES ('20231230', '0700-0900', 'B', 2);",
    "INSERT INTO raffle_status (date, time, park_id, num_of_applications) VALUES ('20231230', '0900-1100', 'B', 3);",
    "INSERT INTO raffle_status (date, time, park_id, num_of_applications) VALUES ('20231231', '0900-1100', 'B', 4);",
})
class RaffleRepositoryTest {

    IRaffleRepository repository;

    @Autowired
    IRaffleMapper mapper;

    @BeforeEach
    void setup() {
        repository = new RaffleRepository(mapper);
    }

    @Test
    void givenOneDate_whenGetRaffleStatus_thenReturnCorrespondingRaffleStatus() {
        var result = repository.getRaffleStatus(List.of("20231230"), null, null);

        assertEquals(3, result.size());
        assertRaffle(result.get(0), "20231230", "0700-0900", "A", "Park A", 1, 1);
        assertRaffle(result.get(1), "20231230", "0700-0900", "B", "Park B", 2, 2);
        assertRaffle(result.get(2), "20231230", "0900-1100", "B", "Park B", 2, 3);
    }

    @Test
    void givenTwoDates_whenGetRaffleStatus_thenReturnCorrespondingRaffleStatus() {
        var result = repository.getRaffleStatus(List.of("20231230", "20231231"), null, null);

        assertEquals(4, result.size());
        assertRaffle(result.get(0), "20231230", "0700-0900", "A", "Park A", 1, 1);
        assertRaffle(result.get(1), "20231230", "0700-0900", "B", "Park B", 2, 2);
        assertRaffle(result.get(2), "20231230", "0900-1100", "B", "Park B", 2, 3);
        assertRaffle(result.get(3), "20231231", "0900-1100", "B", "Park B", 2, 4);
    }

    @Test
    void givenOneTime_whenGetRaffleStatus_thenReturnCorrespondingRaffleStatus() {
        var result = repository.getRaffleStatus(null, List.of("0700-0900"), null);

        assertEquals(2, result.size());
        assertRaffle(result.get(0), "20231230", "0700-0900", "A", "Park A", 1, 1);
        assertRaffle(result.get(1), "20231230", "0700-0900", "B", "Park B", 2, 2);
    }

    @Test
    void givenTwoTimes_whenGetRaffleStatus_thenReturnCorrespondingRaffleStatus() {
        var result = repository.getRaffleStatus(null, List.of("0700-0900", "0900-1100"), null);

        assertEquals(4, result.size());
        assertRaffle(result.get(0), "20231230", "0700-0900", "A", "Park A", 1, 1);
        assertRaffle(result.get(1), "20231230", "0700-0900", "B", "Park B", 2, 2);
        assertRaffle(result.get(2), "20231230", "0900-1100", "B", "Park B", 2, 3);
        assertRaffle(result.get(3), "20231231", "0900-1100", "B", "Park B", 2, 4);
    }

    @Test
    void givenOnePark_whenGetRaffleStatus_thenReturnCorrespondingRaffleStatus() {
        var result = repository.getRaffleStatus(null, null, List.of("A"));

        assertEquals(1, result.size());
        assertRaffle(result.get(0), "20231230", "0700-0900", "A", "Park A", 1, 1);
    }

    @Test
    void givenTwoParks_whenGetRaffleStatus_thenReturnCorrespondingRaffleStatus() {
        var result = repository.getRaffleStatus(null, null, List.of("A" , "B"));

        assertEquals(4, result.size());
        assertRaffle(result.get(0), "20231230", "0700-0900", "A", "Park A", 1, 1);
        assertRaffle(result.get(1), "20231230", "0700-0900", "B", "Park B", 2, 2);
        assertRaffle(result.get(2), "20231230", "0900-1100", "B", "Park B", 2, 3);
        assertRaffle(result.get(3), "20231231", "0900-1100", "B", "Park B", 2, 4);
    }

    @Test
    void givenTimeAndDate_whenGetRaffleStatus_thenReturnCorrespondingRaffleStatus() {
        var result = repository.getRaffleStatus(List.of("20231230"), List.of("0900-1100"), null);

        assertEquals(1, result.size());
        assertRaffle(result.get(0), "20231230", "0900-1100", "B", "Park B", 2, 3);
    }

    @Test
    void givenTimeAndPark_whenGetRaffleStatus_thenReturnCorrespondingRaffleStatus() {
        var result = repository.getRaffleStatus(List.of("20231230"), null, List.of("B"));

        assertEquals(2, result.size());
        assertRaffle(result.get(0), "20231230", "0700-0900", "B", "Park B", 2, 2);
        assertRaffle(result.get(1), "20231230", "0900-1100", "B", "Park B", 2, 3);
    }

    @Test
    void givenDateAndPark_whenGetRaffleStatus_thenReturnCorrespondingRaffleStatus() {
        var result = repository.getRaffleStatus(null, List.of("0900-1100"), List.of("B"));

        assertEquals(2, result.size());
        assertRaffle(result.get(0), "20231230", "0900-1100", "B", "Park B", 2, 3);
        assertRaffle(result.get(1), "20231231", "0900-1100", "B", "Park B", 2, 4);
    }

    private void assertRaffle(
        RaffleStatusEntity result,
        String date,
        String time,
        String parkId,
        String parkName,
        int numOfCourts,
        int numOfApplications
    ) {
        assertEquals(date, result.getDate());
        assertEquals(time, result.getTime());
        assertEquals(parkId, result.getParkId());
        assertEquals(parkName, result.getParkName());
        assertEquals(numOfCourts, result.getNumOfCourts());
        assertEquals(numOfApplications, result.getNumOfApplications());
    }
}
