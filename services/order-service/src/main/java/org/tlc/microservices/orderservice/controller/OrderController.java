package org.tlc.microservices.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.services.OrderService;
import org.tlc.microservices.orderservice.services.OrderValidator;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    OrderValidator validator;
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public void makeOrder(@RequestBody OrderRequestDTO orderRequestDTO){

        // validate order
        Response resp = validator.validate(orderRequestDTO);
        if (!resp.isSuccess()) {
            throw new RuntimeException(resp.getMessage());
        }
        System.out.println(orderRequestDTO);
        orderService.placeOrder(orderRequestDTO);
        orderService.saveOrder(orderRequestDTO);
    }


}
