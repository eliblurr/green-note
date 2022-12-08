package org.tlc.microservices.orderservice.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.tlc.microservices.orderservice.dto.OrderRequestDTO;
import org.tlc.microservices.orderservice.Response;
import org.tlc.microservices.orderservice.dto.enums.Side;

//validator takes data from market data service and ensures that the order can be made

@Service
public class OrderValidator {
    private final Response VALID_ORDER = new Response(true, "order is valid");
    private final Response INVALID_QUANTITY = new Response(false, "user does not have enough of the product in their inventory");
    private final Response UNREASONABLE_PRICE = new Response(false, "Price set is unlikely to be accepted by exchange");
    private final Response INSUFFICIENT_FUNDS = new Response(false, "Not enough funds in account for this transaction");
    private final Response INVALID_REQUEST = new Response(false, "Request received was not valid");

    public Response validate(OrderRequestDTO order) {

//        make request ot user service for inventory data
//        ClientInventoryDTO
        if(order.getSide().equals(Side.SELL)){
            int numberOfProductInInventory = 10_000;// data received from inventory service
            int numberOfProductsToSell = order.getQuantity();
            if(numberOfProductInInventory<numberOfProductsToSell){
                return INVALID_QUANTITY;
            }else{
                return checkIfExchangeWillAcceptOrder(order);
            }
        }else if (order.getSide().equals(Side.BUY)){
            double accountBalance = 0;
            double orderTotal = order.getQuantity() * order.getPrice();
            if (accountBalance < orderTotal){
               return INSUFFICIENT_FUNDS;
            }else{
                return checkIfExchangeWillAcceptOrder(order);
            }
        }else{
            return INVALID_REQUEST;
        }
    }

    public Response checkIfExchangeWillAcceptOrder(OrderRequestDTO order){
        double maxPriceShift = 1;//retrieve from market data service
        double lastTradedPrice = 0; // retrieve from market data service
        double askPrice = 1.5; // from market data service. determines the lowest price being sold at.
        //acceptable bids lastTradedPrice for buying and askPrice for selling,
        // +/-  maxPriceShift

        if (order.getSide().equals(Side.SELL)){
            if(Math.abs(order.getPrice() - askPrice) <maxPriceShift){
                return VALID_ORDER;
            } else{
                return UNREASONABLE_PRICE;
            }
        }

        if (order.getSide().equals(Side.BUY)){
            if(Math.abs(order.getPrice() - lastTradedPrice) < maxPriceShift ){
                return VALID_ORDER;
            } else{
                return UNREASONABLE_PRICE;
            }
        }
        return INVALID_REQUEST;
    }
}
