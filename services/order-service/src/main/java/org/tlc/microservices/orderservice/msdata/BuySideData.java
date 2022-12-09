package org.tlc.microservices.orderservice.msdata;

import lombok.Data;

@Data
public class BuySideData {
    private double maxPriceShift;//retrieve from market data service
    private double lastTradedPrice; // retrieve from market data service

}
