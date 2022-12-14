package org.tlc.microservices.orderservice.services.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.dto.SaveLegDTO;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.data.MarketData;
import org.tlc.microservices.orderservice.services.OrderExecutor;

import java.util.ArrayList;
import java.util.List;

@Component
public class MultiExchange {
    @Autowired
    MarketData marketData;
    @Autowired
    OrderExecutor orderExecutor;

    public List<SaveLegDTO> processOrder(SaveOrderDTO order, List<String> exchangesToPlaceOn) {
        List<SaveLegDTO> trades = new ArrayList<>();
        for(String exchange : exchangesToPlaceOn){
            trades.add(orderExecutor.placeOrder(order, exchange));
        }
        return trades;
    }
}
