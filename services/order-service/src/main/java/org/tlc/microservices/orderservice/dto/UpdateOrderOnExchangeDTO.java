package org.tlc.microservices.orderservice.dto;

import lombok.Data;

@Data
public class UpdateOrderOnExchangeDTO {
    public UpdateOrderOnExchangeDTO(UpdateOrderDTO updateOrderDTO) {
        this.product = updateOrderDTO.getProduct();
        this.quantity = updateOrderDTO.quantity;
        this.price = updateOrderDTO.price;
        this.side = updateOrderDTO.side;
    }

    public String product;
    public String quantity;
    public String price;
    public String side;
}
