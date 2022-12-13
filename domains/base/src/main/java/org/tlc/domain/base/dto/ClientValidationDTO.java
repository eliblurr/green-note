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

    private double portfolioBalance;
    private Boolean userOwnsPortfolio;
    private Boolean canShort;
    private int productQuantity;
    private Boolean portfolioHasProduct;
    private double productTotalValue;
    private Boolean customerExist;

}
