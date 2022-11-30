package org.tlc.microservices.orderservice.service;

import org.tlc.microservices.orderservice.dto.OrderRequest;
import org.tlc.microservices.orderservice.dto.Response;

public interface OrderProcessor {
    Response processOrder(OrderRequest newOrder);
}
