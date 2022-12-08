package org.tlc.microservices.orderservice.dto;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
        this.side = orderRequestDTO.getSide().name();
        this.type = orderRequestDTO.getType().name();
    }

}
