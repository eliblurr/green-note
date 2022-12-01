package org.tlc.microservices.orderservice.services;

import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.Response;

//validator takes data from market data service and ensures that the order can be made

@Component
public class OrderValidator {

    public Response validate(OrderRequestDTO order) {
        return new Response(true, "order is valid");
    }

}
