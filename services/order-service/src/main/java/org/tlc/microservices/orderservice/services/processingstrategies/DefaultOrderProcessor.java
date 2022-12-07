package org.tlc.microservices.orderservice.services.processingstrategies;

import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;

@Component
public class DefaultOrderProcessor extends OrderProcessor {

    @Override
    public void processOrder(OrderRequestDTO order) {
        super.placeOrder(order, "https://exchange2.matraining.com/d8e53ba5-0661-4013-bf98-b950ae1f9224/order");
    }
}
