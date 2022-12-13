package org.tlc.domain.base.order.dto;

import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCustomerDTO {
    private UUID clientId;
    private String product;
    private UUID portfolioId;
}
