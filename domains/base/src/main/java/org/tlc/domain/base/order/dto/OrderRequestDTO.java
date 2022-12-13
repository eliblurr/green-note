package org.tlc.domain.base.order.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.OrderType;
import org.tlc.domain.base.order.enums.Side;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class OrderRequestDTO {
    private UUID clientId;
    private String product;
    private double price;
    private int quantity;
    private UUID portfolioId;
    private Side side;
    private OrderPosition position;
    private OrderType type;


}
