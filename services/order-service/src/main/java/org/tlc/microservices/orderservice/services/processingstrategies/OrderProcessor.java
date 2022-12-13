package org.tlc.microservices.orderservice.services.processingstrategies;


import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.domain.base.order.dto.SaveLegDTO;


public interface OrderProcessor {

    SaveLegDTO processOrder(SaveOrderDTO order);

}
