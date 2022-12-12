package org.tlc.microservices.orderservice.services.processingstrategies;

import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.configuration.dto.SaveTradeDTO;

@Component
public class MultiExchangeOrderProcessor implements OrderProcessor {
    @Override
    public SaveTradeDTO processOrder(SaveOrderDTO order) {
        return null;
    }

//    private List<ExchangeDTO>;
}
