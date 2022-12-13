package org.tlc.domain.base.order.dto;

import lombok.*;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.enums.LegStatus;

import java.util.UUID;

@Data
public class SaveLegDTO {
    private UUID tradeId;
    private UUID order;
    private LegStatus status;
    private int quantity;
    private Side side;
    private UUID exchangeId;
    private double price;


    public SaveLegDTO(SaveTradeDTOBuilder builder) {
        this.tradeId = builder.tradeId;
        this.order = builder.orderId;
        this.status = builder.status;
        this.quantity = builder.quantity;
        this.side = builder.side;
        this.exchangeId = builder.exchangeId;
        this.price = builder.price;
    }

    public static class SaveTradeDTOBuilder{
        private UUID tradeId;
        private UUID orderId;
        private LegStatus status;
        private int quantity;
        private Side side;
        private UUID exchangeId;
        private double price;

        public SaveTradeDTOBuilder(UUID tradeId, UUID orderId, LegStatus status, int quantity, Side side, UUID exchangeId, double price) {
            this.tradeId = tradeId;
            this.orderId = orderId;
            this.status = status;
            this.quantity = quantity;
            this.side = side;
            this.exchangeId = exchangeId;
            this.price = price;
        }
        public SaveLegDTO build(){
            return new SaveLegDTO(this);
        }
    }
}
