package org.tlc.microservices.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.services.OrderService;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/orders")
    public Response makeOrder(@Validated @RequestBody OrderRequestDTO order) {
        System.out.println(order);
        return orderService.placeOrder(order);
    }

//    @PutMapping("/orders")
//    public Response updateOrder(@Validated @RequestBody ){
//
//    }


}
