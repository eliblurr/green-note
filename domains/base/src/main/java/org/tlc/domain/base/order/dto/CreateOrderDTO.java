package org.tlc.domain.base.order.dto;

import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.*;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {

    private UUID id;
    private UUID exchangeOrderId;
    private UUID customer;
    private String product;
    private double price;
    private int quantity;
    private UUID portfolio;
    private Side side;
    private OrderPosition position;
    private OrderStatus status;
    private OrderSplit split;

    //    legs - can be a list of tradeIds -- define final schema tomorrow


    public CreateOrderDTO(SaveOrderDTO saveOrderDTO) {
        this.exchangeOrderId = saveOrderDTO.getOrderId();
        this.customer = saveOrderDTO.getClientId();
        this.product = saveOrderDTO.getProduct();
        this.price = saveOrderDTO.getPrice();
        this.quantity = saveOrderDTO.getQuantity();
        this.portfolio = saveOrderDTO.getPortfolioId();
        this.side = saveOrderDTO.getSide();
        this.position = saveOrderDTO.getPosition();
        this.status = saveOrderDTO.getStatus();
    }
}
