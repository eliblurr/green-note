package org.tlc.microservices.userservice.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.enums.Side;
import org.tlc.microservices.userservice.model.Portfolio;
import org.tlc.microservices.userservice.model.PortfolioProduct;

@Setter
@Getter
@Component
public class CreatePortfolioProductDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    private Side side;
    private String ticker;
    private Integer quantity;
    private Portfolio portfolio;

    public PortfolioProduct convertToEntity() throws RuntimeException {
        return modelMapper.map(this, PortfolioProduct.class);
    }

}
