package org.tlc.microservices.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.services.OrderService;
import org.tlc.microservices.orderservice.services.processingstrategies.OrderProcessor;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/orders")
//    @ResponseStatus(HttpStatus.OK)
    public Response makeOrder(@RequestBody OrderRequestDTO order) {
        return orderService.placeOrder(order);
    }


}
