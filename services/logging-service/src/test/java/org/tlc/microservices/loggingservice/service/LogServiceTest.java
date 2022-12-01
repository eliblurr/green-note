package org.tlc.microservices.loggingservice.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.tlc.microservices.loggingservice.dto.CreateLogDTO;
import org.tlc.microservices.loggingservice.enums.Ops;
import org.tlc.microservices.loggingservice.model.Log;
import org.tlc.microservices.loggingservice.repository.LogRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

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