package org.tlc.microservices.orderservice.data;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.marketData.OrderingServiceDto;

@Component
@Data
public class MarketData {
    private OrderingServiceDto marketData;

}
