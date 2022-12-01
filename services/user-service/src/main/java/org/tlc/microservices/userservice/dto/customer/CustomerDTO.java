package org.tlc.microservices.userservice.dto.customer;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.model.Customer;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Component
public class CustomerDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    private UUID id;
    private String email;
    private Double balance;
    private String username;
    private Boolean can_short;
    private Timestamp updated;
    private Timestamp created;
    private Boolean is_active;

    public static CustomerDTO convertToDTO(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }

}
