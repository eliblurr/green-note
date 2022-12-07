package org.tlc.microservices.orderservice.services.processingstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;

@Component
public class MasterOrderProcessor {
    @Autowired
    DefaultOrderProcessor defaultOrderProcessor;

    public void processOrder(OrderRequestDTO order){
        if(order.getType().equals("LIMIT")){
            //extract some data from request and compare to data from exchange
        } else if(order.getType().equals("MARKET") ){
            defaultOrderProcessor.processOrder(order);
        } {

        }
//        else{
//            return new Response(false, "Order type is invalid");
//        }
    }
}
