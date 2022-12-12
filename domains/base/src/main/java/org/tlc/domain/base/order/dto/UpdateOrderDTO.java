package org.tlc.domain.base.order.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.tlc.domain.base.order.enums.OrderStatus;

import java.util.UUID;

@Setter
@Getter
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateOrderDTO {
    private UUID orderId;
    private OrderStatus status;
}
