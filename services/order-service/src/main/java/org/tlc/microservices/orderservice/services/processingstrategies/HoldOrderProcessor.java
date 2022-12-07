package org.tlc.microservices.orderservice.services.processingstrategies;
import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;

import java.util.Arrays;
import java.util.OptionalDouble;

@Component
public class HoldOrderProcessor extends OrderProcessor {
    public void processOrder(OrderRequestDTO newOrder) {

        //prices here is a mock of data the exchange will send
        double[] prices = { 1.2, 1.12, 1.15, 1.24, 1.22, 1.18, 1.19, 1.16, 1.14, 1.1};
        //when new market data is received, if orderNow() is true place order else keep waiting. How to do determine how long to wait
        OptionalDouble average = Arrays.stream(prices).average();
        double lastPrice = prices[0];

        if(orderNow(lastPrice, average.getAsDouble())){
        super.placeOrder(newOrder, "https://exchange2.matraining.com/d8e53ba5-0661-4013-bf98-b950ae1f9224/order");
        }

    }
    public boolean orderNow(double lastPrice, double average){
            return average> lastPrice;
    }
}
