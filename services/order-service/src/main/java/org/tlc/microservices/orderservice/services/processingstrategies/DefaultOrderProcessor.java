package org.tlc.microservices.orderservice.services.processingstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.configuration.dto.SaveTradeDTO;
import org.tlc.microservices.orderservice.services.OrderExecutor;

@Component
public class DefaultOrderProcessor implements OrderProcessor {
    @Autowired
    OrderExecutor orderExecutor;

    @Override
    public SaveTradeDTO processOrder(SaveOrderDTO order) {
        return orderExecutor.placeOrder(order, "MAL1");
    }

}
