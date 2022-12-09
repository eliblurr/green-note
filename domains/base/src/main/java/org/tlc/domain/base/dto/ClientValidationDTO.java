package org.tlc.domain.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ClientValidationDTO {

    private double portfolioBalance = 0;
    private Boolean userOwnsPortfolio = false;
    private Boolean canShort = false;
    private int productQuantity = 0;
    private Boolean portfolioHasProduct = false;
    private double productTotalValue = 0;
    private Boolean customerExist = false;

}
