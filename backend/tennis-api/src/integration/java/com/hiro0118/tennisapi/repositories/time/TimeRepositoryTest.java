package com.hiro0118.tennisapi.repositories.time;

import com.hiro0118.tennisapi.domain.time.repositoryinterface.ITimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@ActiveProfiles("integration")
@Transactional
class TimeRepositoryTest {

    ITimeRepository repository;

    @Autowired
    ITimeMapper mapper;

    @BeforeEach
    void setup() {
        repository = new TimeRepository(mapper);
    }

    @Test
    @Sql(statements = {
        "INSERT INTO time (time) VALUES ('0700-0900');",
        "INSERT INTO time (time) VALUES ('0900-1100');",
        "INSERT INTO time (time) VALUES ('1100-1300');"
    })
    void test() {
        var result = repository.getTimes();

        String[] expectedTimes = {"0700-0900", "0900-1100", "1100-1300"};
        assertEquals(expectedTimes.length, result.size());
        for(int i = 0; i < expectedTimes.length; i++) {
            assertEquals(expectedTimes[i], result.get(i).getTime());
        }
    }
}
