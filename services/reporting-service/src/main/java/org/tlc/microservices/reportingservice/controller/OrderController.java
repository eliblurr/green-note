package org.tlc.microservices.reportingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tlc.microservices.reportingservice.dto.OrderDTO;
import org.tlc.microservices.reportingservice.services.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
//@CrossOrigin(origins = "http://127.0.0.1:*")
@RequiredArgsConstructor // create constructor with required arguments we need at compile time
public class OrderController {

    @Autowired private final OrderService orderService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> read(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "created, asc") String[] sort,
            @RequestParam(required = false) UUID customer
    ) { return orderService.read(page, size, sort, customer); }

    @GetMapping(value = {"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.OK)
    OrderDTO readById(@PathVariable("id") UUID id){
        return orderService.readById(id);
    }

}
