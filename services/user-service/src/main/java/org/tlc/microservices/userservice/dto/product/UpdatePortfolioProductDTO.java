package org.tlc.microservices.userservice.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePortfolioProductDTO {
    @Nullable private Integer quantity;
}
