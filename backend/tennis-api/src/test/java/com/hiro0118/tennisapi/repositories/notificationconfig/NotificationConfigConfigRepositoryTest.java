package com.hiro0118.tennisapi.repositories.notificationconfig;

import com.hiro0118.tennisapi.domain.notificationconfig.INotificationConfigRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@MybatisTest
@ActiveProfiles("test")
@Transactional
@Sql(statements = {
    "INSERT INTO time (time) VALUES ('0700-0900');",
})
class NotificationConfigConfigRepositoryTest {

    INotificationConfigRepository repository;

//    @Autowired
//    INotificationConfigMapper mapper;

    @BeforeEach
    void setup() {
//        repository = new NotificationConfigConfigRepository(mapper);
    }

    @Test
    void given_when_then() {

    }

}