package org.tlc.microservices.orderservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.enums.TradeStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SaveTradeDTO {
    private String tradeID;
    private UUID orderID;
    private TradeStatus status;
    private int quantity;
    private Side side;
    private String exchangeID;
    private double price;
    private LocalDateTime created;


    public SaveTradeDTO(SaveTradeDTOBuilder builder) {
        this.tradeID = builder.tradeID;
        this.orderID = builder.orderID;
        this.status = builder.status;
        this.quantity = builder.quantity;
        this.side = builder.side;
        this.exchangeID = builder.exchangeID;
        this.price = builder.price;
        this.created = builder.created;
    }

    public static class SaveTradeDTOBuilder{
        private String tradeID;
        private UUID orderID;
        private TradeStatus status;
        private int quantity;
        private Side side;
        private String exchangeID;
        private double price;
        private LocalDateTime created;

        public SaveTradeDTOBuilder(String tradeID, UUID orderID, TradeStatus status, int quantity, Side side, String exchangeID, double price) {
            this.tradeID = tradeID;
            this.orderID = orderID;
            this.status = status;
            this.quantity = quantity;
            this.side = side;
            this.exchangeID = exchangeID;
            this.price = price;
            this.created = LocalDateTime.now();
        }
        public SaveTradeDTO build(){
            return new SaveTradeDTO(this);
        }
    }
}
