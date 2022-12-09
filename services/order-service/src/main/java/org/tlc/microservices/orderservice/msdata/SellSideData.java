package org.tlc.microservices.orderservice.msdata;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Data
@Component
public class SellSideData {
    private double maxPriceShift;
    private double askPrice ;
}
