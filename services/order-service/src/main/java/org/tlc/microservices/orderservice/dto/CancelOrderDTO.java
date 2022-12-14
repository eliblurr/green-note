package org.tlc.microservices.orderservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CancelOrderDTO {
    private UUID clientId;
    private UUID orderId;
    private UUID portfolioId;

}
