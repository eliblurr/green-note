package org.tlc.microservices.orderservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.services.OrderService;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/orders")
//    @ResponseStatus(HttpStatus.OK)
    public Response makeOrder(@Validated @RequestBody OrderRequestDTO order) {
        return orderService.placeOrder(order);
    }


}
