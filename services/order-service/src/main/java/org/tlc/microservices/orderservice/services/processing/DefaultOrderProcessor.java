package org.tlc.microservices.orderservice.services.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.domain.base.order.dto.SaveLegDTO;
import org.tlc.microservices.orderservice.services.OrderExecutor;

@Component
public class DefaultOrderProcessor{
    @Autowired
    OrderExecutor orderExecutor;

    public SaveLegDTO processOrder(SaveOrderDTO order) {
        return orderExecutor.placeOrder(order, "MAL1");
    }

}
