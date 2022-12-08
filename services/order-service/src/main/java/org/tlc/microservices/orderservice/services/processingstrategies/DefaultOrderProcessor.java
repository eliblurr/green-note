package org.tlc.microservices.orderservice.services.processingstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;

@Component
public class DefaultOrderProcessor extends OrderProcessor {
//    @Autowired
//    OrderProcessor orderProcessor;


    @Override
    public void processOrder(OrderRequestDTO order) {
        super.placeOrder(order,"MAL1");
    }
}
