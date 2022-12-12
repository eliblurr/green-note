package org.tlc.domain.base.order.dto;

import lombok.*;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.OrderType;
import org.tlc.domain.base.order.enums.Side;

import java.time.LocalDateTime;
import java.util.UUID;

//@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveOrderDTO {
    private UUID orderId;
    private UUID clientId;
    private String product;
    private int quantity;
    private double price;
    private Side side;
    private OrderType type;
    private UUID portfolioId;
    private OrderPosition position;
    private OrderStatus status;
    LocalDateTime created;

    public SaveOrderDTO(OrderRequestDTO orderRequest, OrderStatus status) {
        this.orderId = UUID.randomUUID();
        this.clientId = orderRequest.getClientId();
        this.product = orderRequest.getProduct();
        this.quantity = orderRequest.getQuantity();
        this.price = orderRequest.getPrice();
        this.side = orderRequest.getSide();
        this.type = orderRequest.getType();
        this.portfolioId = orderRequest.getPortfolioId();
        this.position = orderRequest.getPosition();
        this.status = status;
        this.created = LocalDateTime.now();
    }
}
