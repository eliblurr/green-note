package org.tlc.microservices.reportingservice.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tlc.microservices.reportingservice.model.Leg;
import org.tlc.microservices.reportingservice.repository.LegRepository;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)

class TradeServiceTest {
    @Autowired
    LegRepository tradeRepository;
    @Test
    void retrieveAllDataFromDBTest_all() {
        //given a tradeRepository and a fixed number of rows
        int expected =2;
        //when I try to retrieve all rows from the db
        List<Leg> tradesStored = tradeRepository.findAll();
        int actual = tradesStored.size();

        //then assert that the rows returned are equal to the rows in the db
        Assertions.assertEquals(expected,actual);
    }
}