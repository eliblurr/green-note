/*
package org.tlc.microservices.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.marketData.OrderingServiceDto;
import org.tlc.domain.base.marketData.TickerPriceDto;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.dto.SaveOrderDTO;
import org.tlc.microservices.orderservice.data.MarketData;
import org.tlc.microservices.orderservice.services.processing.DefaultOrderProcessor;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;

@Component
public class OrderProcessorPicker {
    @Autowired
    DefaultOrderProcessor defaultOrderProcessor;
//    @Autowired
//    SplitOrderProcessor splitOrderProcessor;
    @Autowired
    MarketData marketData;




    public void processOrder(SaveOrderDTO order) {
        //extract order data from orderdto
        String product = order.getProduct();
        String side = order.getSide().name();
        double limitForPrice = order.getPrice();
        double orderTotal = order.getQuantity() * order.getPrice();
        ///if md is empty just place default order

        //extract some data from request and compare to data from exchange
//        OrderingServiceDto marketdata = marketData.getMarketData();


//        List<TickerPriceDto> lastTenPricesSorted = marketData.getLastTenPrices(Side side);

          List<List<TickerPriceDto>> exchangesWithLastTenPrices;




        ///if md is empty just place default order
        if (!) {


            //first check the exchanges the order exists on
            //currently getting data from just 1 exchange

            //next check for if the order can be split into multiple legs
            //first retrieve the last 10 prices available to be traded at
            // then check if order can span multiple of these
//            Iterator<TickerPriceDto> queueIterator = lastTenPrices.iterator();
//            if(order.getSide().equals(Side.BUY)){
//                if(limitForPrice > lastTenPrices.element().getPrice() && order.getQuantity() > lastTenPrices.element().getQuantity()){
////                splitOrderProcessor.processOrder(order);
//                }
//            }

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
*/
