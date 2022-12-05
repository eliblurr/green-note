package org.tlc.microservices.orderservice.dto;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.enums.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderRequestDTO {
   // @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

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
