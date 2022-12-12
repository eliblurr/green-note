package org.tlc.microservices.orderservice.configuration.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CancelOrderDTO {
    private UUID clientId;
    private UUID orderId;
    private String exchangeKey;
}
