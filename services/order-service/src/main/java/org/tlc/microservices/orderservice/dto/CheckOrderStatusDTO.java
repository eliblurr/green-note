package org.tlc.microservices.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CheckOrderStatusDTO {
    @NotNull private UUID clientID;
    @NotNull private String exchangeID;
}
