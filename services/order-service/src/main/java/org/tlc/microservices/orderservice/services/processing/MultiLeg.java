package org.tlc.microservices.orderservice.services.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.dto.SaveLegDTO;
import org.tlc.microservices.orderservice.configuration.ExchangesConfig;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.services.OrderExecutor;

@Component
public class MultiLeg {
    @Autowired
    ExchangesConfig exchangesConfig;
    @Autowired
    OrderExecutor orderExecutor;
//    List<Exchange> exchanges;
    public SaveLegDTO processOrder(SaveOrderDTO order) {
        return orderExecutor.placeOrder(order,"MAL1");
    }
}
