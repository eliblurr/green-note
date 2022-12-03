package org.tlc.microservices.userservice.dto.portfolio;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePortfolioDTO {

    @Nullable private String name;
    @Nullable private Boolean is_default;
    @Nullable private Boolean is_active;

}
