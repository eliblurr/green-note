package org.tlc.microservices.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.enums.*;


@Component
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
    private String side;
    private OrderPosition position;
    private OrderStatus status;
    private String type;


}
