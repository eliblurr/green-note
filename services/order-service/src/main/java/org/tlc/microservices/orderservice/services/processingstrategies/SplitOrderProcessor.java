package org.tlc.microservices.orderservice.services.processingstrategies;

import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;

@Component
public class SplitOrderProcessor extends OrderProcessor{
//    List<Exchange> exchanges;
    @Override
    public void processOrder(OrderRequestDTO order) {


    }
}
