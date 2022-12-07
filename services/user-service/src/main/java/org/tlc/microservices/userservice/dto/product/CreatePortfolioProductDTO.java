package org.tlc.microservices.userservice.dto.product;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.enums.Side;
import org.tlc.microservices.userservice.model.Portfolio;
import org.tlc.microservices.userservice.model.PortfolioProduct;

import java.util.UUID;

@Setter
@Getter
@Component
public class CreatePortfolioProductDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    private Side side;
    private String ticker;
    private Integer quantity;
    @Nullable private Portfolio portfolio;

    public PortfolioProduct convertToEntity() throws RuntimeException {
        return modelMapper.map(this, PortfolioProduct.class);
    }

}
