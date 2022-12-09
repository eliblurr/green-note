package org.tlc.microservices.orderservice.msdata;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
public class MarketServiceData {
//    private List<Exchange> exchanges = new ArrayList<Exchange>();

    private double maxPriceShift;
    private double askPrice ;
    private double lastTradedPrice ;

}
