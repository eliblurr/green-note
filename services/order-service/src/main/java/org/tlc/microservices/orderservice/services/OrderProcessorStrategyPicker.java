package org.tlc.microservices.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.microservices.orderservice.dto.CreateOrderOnExchangeDTO;
import org.tlc.microservices.orderservice.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.dto.SaveTradeDTO;
import org.tlc.microservices.orderservice.services.processingstrategies.DefaultOrderProcessor;
import org.tlc.microservices.orderservice.services.processingstrategies.SplitOrderProcessor;

import java.util.Iterator;
import java.util.Queue;

@Component
public class OrderProcessorStrategyPicker {
    @Autowired
    DefaultOrderProcessor defaultOrderProcessor;
    @Autowired
    SplitOrderProcessor splitOrderProcessor;
    @Autowired
    OrderValidator orderValidator;
    @Autowired
    Iterator<Double> queueIterator;



    public void processOrder(SaveOrderDTO order) {
        //extract order data from orderdto
        String product = order.getProduct();
        String side = order.getSide().name();
        double limitForPrice = order.getPrice();
        double orderTotal = order.getQuantity() * order.getPrice();
        ///if md is empty just place default order

        //extract some data from request and compare to data from exchange
        OrderingServiceDto marketdata = orderValidator.getMarketData();


        Queue<Double> lastTenPrices = marketdata.getTickers().get(product).get(side);

        ///if md is empty just place default order
        if (!lastTenPrices.isEmpty()) {


            //first check the exchanges the order exists on
            //currently getting data from just 1 exchange

            //next check for if the order can be split into multiple legs
            //first retrieve the last 10 prices avalailable to be traded at
            // then check if order can span multiple of these
            queueIterator = lastTenPrices.iterator();
            if(order.getSide().equals(Side.BUY)){
                if(limitForPrice > lastTenPrices.price() && order.getQuantity() > LastTenPrices.quantity()){


                }

            }
            if (orderTotal > ) {//if order total is  greater than product available at that price;
                splitOrderProcessor.processOrder(order);
            }
            if(lastTenPrices. > limitForPrice){}

            //

            // after processing all orders return a list of orders


            //then check for the best price (*quantity ratio) on each exchange
            defaultOrderProcessor.processOrder(order);

        }
        else{
            defaultOrderProcessor.processOrder(order);

        }
    }

}
