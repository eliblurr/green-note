package org.tlc.microservices.userservice.dto.portfolio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.dto.customer.CustomerDTO;
import org.tlc.microservices.userservice.model.Customer;
import org.tlc.microservices.userservice.model.Portfolio;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Component
public class PortfolioDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    private UUID id;
    private String name;
    private Boolean is_default;
    private double balance;
    private Timestamp updated;
    private Timestamp created;

    public static PortfolioDTO convertToDTO(Portfolio portfolio){return modelMapper.map(portfolio, PortfolioDTO.class);}

}
