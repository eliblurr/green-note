package org.tlc.microservices.orderservice.strategy;

import org.tlc.microservices.orderservice.dto.OrderRequestDTO;

public interface OrderProcessor {
    public void processOrder(OrderRequestDTO newOrder);
}
