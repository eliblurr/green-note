package org.tlc.microservices.userservice.dto.portfolio;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.model.Admin;
import org.tlc.microservices.userservice.model.Customer;
import org.tlc.microservices.userservice.model.Portfolio;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@Component
public class CreatePortfolioDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    private UUID id;
    private String name;
    private Boolean is_default;
    private Timestamp updated;
    private Timestamp created;
    private Customer customer;

    public Portfolio convertToEntity() throws RuntimeException {
        return modelMapper.map(this, Portfolio.class);
    }

}
