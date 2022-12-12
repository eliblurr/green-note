package org.tlc.domain.base.order.dto;

import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.*;
import java.util.UUID;

@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CreateLegDTO {

    private LegStatus status;
    private int quantity;
    private Side side;
    private UUID exchangeId;
    private double price;
    @Nullable private UUID order;
    private UUID tradeId;

}
