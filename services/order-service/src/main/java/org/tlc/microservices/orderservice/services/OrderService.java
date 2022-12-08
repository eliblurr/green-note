package org.tlc.microservices.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.dto.enums.OrderStatus;
import org.tlc.microservices.orderservice.services.processingstrategies.DefaultOrderProcessor;

@Service
public class OrderService {

    @Autowired
    private DefaultOrderProcessor orderProcessor;

    @Autowired
    private OrderValidator validator;

    @Autowired
    private OrderPublisher orderPublisher;







    public Response placeOrder(@Validated OrderRequestDTO order){
        // validate order
        Response resp = validator.validate(order);
        if (!resp.isSuccess()) {
            order.setStatus(OrderStatus.REJECTED);
            orderPublisher.saveOrder(order);
            return resp;
        }

        orderProcessor.processOrder(order);
        order.setStatus(OrderStatus.ACCEPTED);
        orderPublisher.saveOrder(order);
        return resp;
    }
}
