package org.tlc.microservices.orderservice.services.processingstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.dto.SaveTradeDTO;

@Component
public class DefaultOrderProcessor extends OrderProcessor {
//    @Autowired
//    OrderProcessor orderProcessor;

    @Override
    public SaveTradeDTO processOrder(SaveOrderDTO order) {
        return super.placeOrder(order,"MAL1");
    }

}
