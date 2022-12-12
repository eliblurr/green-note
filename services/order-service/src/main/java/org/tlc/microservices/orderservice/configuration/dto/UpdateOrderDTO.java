package org.tlc.microservices.orderservice.configuration.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class UpdateOrderDTO {
    public UUID clientId;
    public String product;
    public String quantity;
    public String price;
    public String side;
    public String orderId;
    public String exchangeKey;
}
