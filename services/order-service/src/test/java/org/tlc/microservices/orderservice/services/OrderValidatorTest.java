package org.tlc.microservices.orderservice.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tlc.domain.base.order.dto.OrderRequestDTO;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.enums.OrderType;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.services.validation.ClientDataFetcher;
import org.tlc.microservices.orderservice.services.validation.ClientValidationService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderValidatorTest {
    @Autowired
    OrderValidator orderValidator;
    @MockBean
    ClientDataFetcher clientDataFetcher;
//

    @Test
    public void validate_SellOrderWithValidQuantityTest(){
        //given an order from a client who has enough stock in his inventory
        UUID clientId = UUID.randomUUID();
        UUID portfolioId = UUID.randomUUID();
        OrderRequestDTO order = new OrderRequestDTO(clientId, "APPL", 1.5, 100, portfolioId, Side.SELL, OrderPosition.NORMAL, OrderType.MARKET);

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
        UUID clientId = UUID.randomUUID();
        UUID portfolioId = UUID.randomUUID();
        OrderRequestDTO order = new OrderRequestDTO(clientId, "APPL", 1.5, 100, portfolioId, Side.SELL, OrderPosition.NORMAL, OrderType.MARKET);

        int numberOfProductInInventory;// for test to pass you wil need to mock the service that gives the data from user service inventory endpoint
        //this test doesn't work

        //when the validate method is called
        Response resp = orderValidator.validate(order);

        //then assert that the request is valid
        assertFalse(resp.isSuccess());
    }

}