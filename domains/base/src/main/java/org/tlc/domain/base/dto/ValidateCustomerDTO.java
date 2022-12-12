package org.tlc.domain.base.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Setter
@Getter
@Component
public class ValidateCustomerDTO {

    private UUID portfolio;
    private UUID customer;
    private UUID product;

}
