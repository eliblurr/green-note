package org.tlc.microservices.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.enums.OrderStatus;
import org.tlc.microservices.orderservice.services.OrderService;
import org.tlc.microservices.orderservice.services.OrderValidator;
import org.tlc.microservices.orderservice.strategy.DefaultOrderProcessor;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    OrderValidator validator;

    @Autowired
    private DefaultOrderProcessor orderProcessor;
    @PostMapping("/orders")
//    @ResponseStatus(HttpStatus.OK)
    public HttpStatus makeOrder(@RequestBody OrderRequestDTO orderRequestDTO){

        // validate order
        Response resp = validator.validate(orderRequestDTO);
        if (!resp.isSuccess()) {
            orderRequestDTO.setStatus(OrderStatus.REJECTED);
            orderService.saveOrder(orderRequestDTO);
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }

        //for valid order choose strategy
        //use strategy to process order
        orderProcessor.processOrder(orderRequestDTO);

        orderRequestDTO.setStatus(OrderStatus.ACCEPTED);
        orderService.saveOrder(orderRequestDTO);
        return HttpStatus.CREATED;
    }


}
