package org.tlc.microservices.orderservice.services.processingstrategies;


import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.dto.SaveTradeDTO;


public interface OrderProcessor {

    SaveTradeDTO processOrder(SaveOrderDTO order);

}
