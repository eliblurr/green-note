package org.tlc.microservices.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.enums.OrderPosition;
import org.tlc.microservices.orderservice.dto.enums.OrderStatus;
import org.tlc.microservices.orderservice.dto.enums.Side;
import org.tlc.microservices.orderservice.services.OrderService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
class OrderControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void makeOrderWithValidRequestTest(){
        //g
//        OrderRequestDTO orderRequest = new OrderRequestDTO(2,"APPL",1.5,100,1, Side.BUY, OrderPosition.NORMAL,OrderStatus.ACCEPTED);

//        doReturn(HttpStatus.OK).when(orderService).saveOrder(any());
        //w

        //then verify that both methods were called
//        verify();
    }

}