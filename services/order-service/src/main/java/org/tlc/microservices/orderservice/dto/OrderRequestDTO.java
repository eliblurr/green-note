package org.tlc.microservices.orderservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.OrderType;
import org.tlc.domain.base.order.enums.Side;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderRequestDTO {
    private int clientID;
    private String product;
    private double price;
    private int quantity;
    private int portfolioID;
    private Side side;
    private OrderPosition position;
    private OrderType type;


}
