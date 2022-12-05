package org.tlc.microservices.orderservice.dto;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.enums.OrderPosition;
import org.tlc.microservices.orderservice.dto.enums.OrderStatus;
import org.tlc.microservices.orderservice.dto.enums.Side;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CreateOrderOnExchangeDTO {
 //DTO for placing order on exchange
   // @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

    private String product;
    private int quantity;
    private double price;
    private String side;
    private String type;

    public CreateOrderOnExchangeDTO(OrderRequestDTO orderRequestDTO) {
        this.product = orderRequestDTO.getProduct();
        this.price = orderRequestDTO.getPrice();
        this.quantity = orderRequestDTO.getQuantity();
        this.side = orderRequestDTO.getSide();
        this.type = orderRequestDTO.getType();
    }

}
