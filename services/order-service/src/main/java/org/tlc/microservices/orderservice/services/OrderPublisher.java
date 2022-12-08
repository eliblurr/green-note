package org.tlc.microservices.orderservice.services;

import org.springframework.stereotype.Service;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;

@Service
public class OrderPublisher {

    public void saveOrder(OrderRequestDTO orderRequest) {
        //send to reporting service
        //this webclient will be replaced with a redis publisher
    }
}
