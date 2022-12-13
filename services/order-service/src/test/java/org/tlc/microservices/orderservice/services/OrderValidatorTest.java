package org.tlc.microservices.orderservice.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tlc.domain.base.order.Response;
import org.tlc.domain.base.order.dto.OrderRequestDTO;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderType;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.microservices.orderservice.services.validation.ClientValidationService;
import org.tlc.microservices.orderservice.services.validation.PriceValidationService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class OrderValidatorTest {
    @Autowired
    OrderValidator orderValidator;
    @MockBean
    ClientValidationService clientValidationService;
    @MockBean
    PriceValidationService priceValidationService;

//    @BeforeAll
//    public static void init(){
//        UUID clientId = UUID.randomUUID();
//        UUID portfolioId = UUID.randomUUID();
//    }



    @Test
    public void validate_SellOrderWithValidQuantityTest(){
        //given an order from a client who has enough stock in his inventory
        UUID clientId = UUID.randomUUID();
        UUID portfolioId = UUID.randomUUID();
        OrderRequestDTO testOrder = new OrderRequestDTO(clientId, "APPL", 1.5, 100, portfolioId, Side.SELL, OrderPosition.NORMAL, OrderType.MARKET);

        Mockito.when(clientValidationService.validateCustomer(any(),any())).thenReturn(Response.VALID_CLIENT);
        Mockito.when(priceValidationService.validatePrice(any())).thenReturn(Response.VALID_ORDER);
        //when the validate method is called
        orderValidator.validate(testOrder);

        //verify that the client validation service was called once
        verify(clientValidationService, times(1)).validateCustomer(any(),any());

        //and that the price validation service was also called
        verify(priceValidationService, times(1)).validatePrice(any());

    }

    @Test
    public void validate_SellOrderWithInvalidQuantityTest(){
        //given an order from a client who does not have enough stock in his inventory
        UUID clientId = UUID.randomUUID();
        UUID portfolioId = UUID.randomUUID();
        OrderRequestDTO testOrder = new OrderRequestDTO(clientId, "APPL", 1.5, 100, portfolioId, Side.SELL, OrderPosition.NORMAL, OrderType.MARKET);

        Mockito.when(clientValidationService.validateCustomer(any(),any())).thenReturn(Response.INVALID_QUANTITY);

        //when the validate method is called
        Response resp = orderValidator.validate(testOrder);

        //then assert that the request is valid
        assertFalse(resp.isSuccess());
        //verify that the client validation service was called once
        verify(clientValidationService, times(1)).validateCustomer(any(),any());

        //and that the price validation service was not called
        verify(priceValidationService, times(0)).validatePrice(any());
    }

}