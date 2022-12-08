package org.tlc.microservices.orderservice.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.enums.OrderPosition;
import org.tlc.microservices.orderservice.dto.enums.OrderStatus;
import org.tlc.microservices.orderservice.dto.enums.OrderType;
import org.tlc.microservices.orderservice.dto.enums.Side;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderValidatorTest {
    @Autowired
    OrderValidator orderValidator;

    @Test
    public void validate_SellOrderWithValidQuantityTest(){
        //given an order from a client who has enough stock in his inventory
        OrderRequestDTO order = new OrderRequestDTO(2, "APPL", 1.5, 100, 1, Side.SELL, OrderPosition.NORMAL, OrderStatus.ACCEPTED, OrderType.MARKET);

         // for test to pass you will need to mock the service that gives the data from user service inventory endpoint
        //this test doesn't work

        //when the validate method is called
        Response resp = orderValidator.validate(order);

        //then assert that the request is valid
        assertTrue(resp.isSuccess());
    }

    @Test
    public void validate_SellOrderWithInvalidQuantityTest(){
        //given an order from a client who does not have enough stock in his inventory
        OrderRequestDTO order = new OrderRequestDTO(2, "APPL", 1.5, 100, 1, Side.SELL, OrderPosition.NORMAL, OrderStatus.ACCEPTED, OrderType.MARKET);

        int numberOfProductInInventory;// for test to pass you wil need to mock the service that gives the data from user service inventory endpoint
        //this test doesn't work

        //when the validate method is called
        Response resp = orderValidator.validate(order);

        //then assert that the request is valid
        assertFalse(resp.isSuccess());
    }

}