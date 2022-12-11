package org.tlc.microservices.reportingservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tlc.microservices.reportingservice.dto.OrderCreationDTO;
import org.tlc.microservices.reportingservice.dto.ReadOrderDTO;
import org.tlc.microservices.reportingservice.services.OrderService;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    List<ReadOrderDTO> all() {
        return orderService.getAllOrders();
    }

//    endpoint to verify there are no active orders on exchange you want to delete

}
