package org.tlc.microservices.orderservice.service;

import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.OrderRequest;
import org.tlc.microservices.orderservice.dto.Response;

@Component
public class OrderValidator {

    public Response validate(OrderRequest order) {
        return new Response(true, "order is valid");
    }

}
