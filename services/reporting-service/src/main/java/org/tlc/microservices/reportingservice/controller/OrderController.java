package org.tlc.microservices.reportingservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.reportingservice.dto.OrderCreationDTO;
import org.tlc.microservices.reportingservice.dto.ReadOrderDTO;
import org.tlc.microservices.reportingservice.services.OrderService;

import java.util.List;

@RestController
public class OrderController {

//    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<ReadOrderDTO> all() {
        return orderService.getAllOrders();
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public  void createOrder(@RequestBody OrderCreationDTO orderCreationDTO){
    orderService.saveOrder(orderCreationDTO);
    }





}
