package org.tlc.microservices.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.enums.OrderStatus;
import org.tlc.microservices.orderservice.services.processingstrategies.MasterOrderProcessor;

@Service
public class OrderService {

//    @Autowired
//    private WebClient webClient;

    @Autowired
    private MasterOrderProcessor masterOrderProcessor;

    @Autowired
    private OrderValidator validator;





    public void saveOrder(OrderRequestDTO orderRequest) {
        //send to reporting service
        //this webclient will be replaced with a redis publisher
    }

    public HttpStatus placeOrder(OrderRequestDTO order){

        // validate order
        Response resp = validator.validate(order);
        if (!resp.isSuccess()) {
            order.setStatus(OrderStatus.REJECTED);
            saveOrder(order);
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }

        //for valid order choose strategy

        //use strategy to process order

        masterOrderProcessor.processOrder(order);

        order.setStatus(OrderStatus.ACCEPTED);
        saveOrder(order);
        return HttpStatus.CREATED;
    }



}
