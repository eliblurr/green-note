package org.tlc.microservices.orderservice.services;

import org.springframework.stereotype.Service;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.SaveOrderDTO;

@Service
public class OrderPublisher {

    public void saveOrder(SaveOrderDTO order) {
        //send to reporting service
        //this webclient will be replaced with a redis publisher
    }
}
