package org.tlc.microservices.orderservice.services;

import org.springframework.stereotype.Service;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.configuration.dto.SaveTradeDTO;

import java.util.List;

@Service
public class OrderPublisher {

    public void saveOrder(SaveOrderDTO order) {
        //send to reporting service
        //this webclient will be replaced with a redis publisher
    }

    public void saveTrades(SaveTradeDTO tradeDTO){

    }

    public void saveTrades(List<SaveTradeDTO> trades){

    }
}
