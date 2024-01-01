package com.hiro0118.tennisapi.repositories.park;

import com.hiro0118.tennisapi.domain.park.ParkEntity;
import com.hiro0118.tennisapi.domain.park.IParkRepository;
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
class ParkRepositoryTest {

    IParkRepository repository;

    @Autowired
    IParkMapper mapper;

    @BeforeEach
    void setup() {
        repository = new ParkRepository(mapper);
    }

    @Test
    @Sql(statements = {
        "INSERT INTO park (id, name, postal_code, address, num_of_courts) " +
            "VALUES ('id1', 'Park A', 1111111, 'address A', 1);",
        "INSERT INTO park (id, name, postal_code, address, num_of_courts) " +
            "VALUES ('id2', 'Park B', 2222222, 'address B', 2);"
    })
    void getParks() {
        List<ParkEntity> result = repository.getParks();

        assertEquals(2, result.size());
        assertPark(result.get(0), "id1", "Park A", 1111111, "address A", 1);
        assertPark(result.get(1), "id2", "Park B", 2222222, "address B", 2);
    }

    private void assertPark(ParkEntity result, String id, String name, int postalCode, String address, int numOfCourts) {
        assertEquals(id, result.getId());
        assertEquals(name, result.getName());
        assertEquals(postalCode, result.getPostalCode());
        assertEquals(address, result.getAddress());
        assertEquals(numOfCourts, result.getNumOfCourts());
    }
}