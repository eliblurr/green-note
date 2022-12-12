package org.tlc.domain.base.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.LegStatus;

import java.util.UUID;

@Setter
@Getter
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateLegDTO {
    private UUID tradeId;
    private LegStatus status;
}
