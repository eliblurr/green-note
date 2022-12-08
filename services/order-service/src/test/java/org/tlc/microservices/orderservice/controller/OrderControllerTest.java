package org.tlc.microservices.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.enums.OrderPosition;
import org.tlc.microservices.orderservice.dto.enums.OrderStatus;
import org.tlc.microservices.orderservice.dto.enums.OrderType;
import org.tlc.microservices.orderservice.dto.enums.Side;
import org.tlc.microservices.orderservice.services.OrderService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @MockBean
    private OrderService orderService;



    @Test
    public void makeOrderWithValidRequestTest() {
        //given an order request DTO

        OrderRequestDTO orderRequest = new OrderRequestDTO(2, "APPL", 200, 100, 1, Side.BUY, OrderPosition.NORMAL, OrderStatus.ACCEPTED, OrderType.MARKET);
        Response expectedResponse = new Response(true, "Order placed successfully");

        doReturn(expectedResponse).when(orderService).placeOrder(any());
        //when the order request is passed to the orderService
        Response actualResponse = orderService.placeOrder(orderRequest);
        //when the orderController receives the order


        //then verify that orderService's placeOrder method was called once
        Assertions.assertEquals(expectedResponse,actualResponse);
    }


}