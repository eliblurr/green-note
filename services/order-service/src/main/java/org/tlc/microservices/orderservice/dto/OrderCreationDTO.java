package org.tlc.microservices.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tlc.microservices.orderservice.dto.enums.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class OrderCreationDTO {
    private int orderID;
    private int clientID;
    private double price;
    private String ticker;
    private Timestamp created;
    private Timestamp updated;
    private OrderStatus status;
    private int quantity;
    private Side side;
    private OrderType type;
    private int portfolioID; //dependency
    private OrderPosition position;

}
