package org.tlc.microservices.userservice.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.enums.Side;
import org.tlc.microservices.userservice.model.Portfolio;
import org.tlc.microservices.userservice.model.PortfolioProduct;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Component
public class PortfolioProductDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    private UUID id;
    private String ticker;
    private Integer quantity;
    private Timestamp updated;
    private Timestamp created;
    private Side side;
//    private Portfolio portfolio;

    public static PortfolioProductDTO convertToDTO(PortfolioProduct product){return modelMapper.map(product, PortfolioProductDTO.class);}

}
