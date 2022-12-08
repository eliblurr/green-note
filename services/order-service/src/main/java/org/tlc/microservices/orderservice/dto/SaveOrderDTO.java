package org.tlc.microservices.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.Side;

import java.time.LocalDateTime;
import java.util.UUID;
//@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveOrderDTO {
    private UUID OrderID;
    private int clientID;
    private String product;
    private double price;
    private int quantity;
    private int portfolioID;
    private Side side;
    private OrderPosition position;
    private OrderStatus status;
    LocalDateTime created;
}
