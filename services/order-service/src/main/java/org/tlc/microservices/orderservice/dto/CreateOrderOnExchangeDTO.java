package org.tlc.microservices.orderservice.dto;

import lombok.*;
import org.tlc.domain.base.order.dto.SaveOrderDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderOnExchangeDTO {
    private String product;
    private int quantity;
    private double price;
    private String side;
    private String type;

    public CreateOrderOnExchangeDTO(SaveOrderDTO orderRequestDTO) {
        this.product = orderRequestDTO.getProduct();
        this.price = orderRequestDTO.getPrice();
        this.quantity = orderRequestDTO.getQuantity();
        this.side = orderRequestDTO.getSide().name();
        this.type = orderRequestDTO.getType().name();
    }

}
