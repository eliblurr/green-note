package org.tlc.microservices.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.OrderType;
import org.tlc.domain.base.order.enums.Side;

import java.time.LocalDateTime;
import java.util.UUID;

//@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveOrderDTO {
    private UUID orderID;
    private int clientID;
    private String product;
    private int quantity;
    private double price;
    private Side side;
    private OrderType type;
    private int portfolioID;
    private OrderPosition position;
    private OrderStatus status;
    LocalDateTime created;

    public SaveOrderDTO(OrderRequestDTO orderRequest, OrderStatus status) {
        this.orderID = UUID.randomUUID();
        this.clientID = orderRequest.getClientID();
        this.product = orderRequest.getProduct();
        this.quantity = orderRequest.getQuantity();
        this.price = orderRequest.getPrice();
        this.side = orderRequest.getSide();
        this.type = orderRequest.getType();
        this.portfolioID = orderRequest.getPortfolioID();
        this.position = orderRequest.getPosition();
        this.status = status;
    }
}
