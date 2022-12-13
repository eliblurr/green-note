package org.tlc.microservices.loggingservice.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tlc.domain.base.logging.enums.Ops;
import org.tlc.microservices.loggingservice.dto.CreateLogDTO;
import org.tlc.microservices.loggingservice.repository.LogRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
//@RunWith(SpringRunner.class)
class LogServiceTest {

    @MockBean
    LogRepository logRepository;

    @MockBean
    CreateLogDTO createLogDTO;

    @Autowired
    LogService logService;

    @Test
    void create() {

        logService.create(Ops.LOGIN, Timestamp.from(Instant.now()), UUID.randomUUID());

        Mockito.verify(logRepository, Mockito.times(1)).save(any());
        Mockito.verify(createLogDTO, Mockito.times(1)).convertToEntity();

    }
}