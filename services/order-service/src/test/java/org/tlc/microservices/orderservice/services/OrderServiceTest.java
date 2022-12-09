package org.tlc.microservices.orderservice.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.domain.base.order.enums.OrderType;
import org.tlc.microservices.orderservice.services.processingstrategies.DefaultOrderProcessor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @MockBean
    DefaultOrderProcessor orderProcessor;

    @MockBean
    OrderValidator orderValidator;

    @MockBean
    OrderPublisher orderPublisher;

    @Test
    void validateValidOrderTest() {
      //GIVEN a valid order request
        OrderRequestDTO order = new OrderRequestDTO(2, "APPL", 1.5, 100, 1, Side.BUY, OrderPosition.NORMAL, OrderStatus.ACCEPTED, OrderType.MARKET);
        //-- and a valid response message
        Response expectedResponse = new Response(true, "Valid Order Request");
        //and a mocked OrderValidator
        doReturn(expectedResponse).when(orderValidator).validate(any());
        //WHEN the order is validated by the service
        Response actualResponse = orderService.placeOrder(order);
        //THEN verify that the order is validated by the validator
        verify(orderValidator, times(1)).validate(order);
        //and processed by the order processor
        verify(orderProcessor, times(1)).processOrder(order);
        //and published to reporting service
        verify(orderPublisher, times(1)).saveOrder(order);
        //and verify that it returns a success response
        Assertions.assertEquals(expectedResponse,actualResponse);
        //and that the order status is set to accepted
        Assertions.assertEquals(OrderStatus.ACCEPTED, order.getStatus());
    }

    @Test
    void validateInvalidOrderTest() {
        //GIVEN an invalid order request
        OrderRequestDTO order = new OrderRequestDTO(2, "APPL", 1.5, 100, -1, Side.BUY, OrderPosition.NORMAL, OrderStatus.ACCEPTED, OrderType.LIMIT);
        //-- and a valid response message
        Response expectedResponse = new Response(false, "Invalid Order Request");
        //and a mocked OrderValidator
        doReturn(expectedResponse).when(orderValidator).validate(any());
        //WHEN the order is validated by the service
        Response actualResponse = orderService.placeOrder(order);
        //THEN verify that the order is validated by the validator
        verify(orderValidator, times(1)).validate(order);
        //and published to reporting service
        verify(orderPublisher, times(1)).saveOrder(order);
        //and verify that it returns a failure response
        Assertions.assertEquals(expectedResponse,actualResponse);
        //and that the order status is set to rejected
        Assertions.assertEquals(OrderStatus.REJECTED, order.getStatus());
    }


}