package org.tlc.microservices.userservice.dto.customer;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.dto.interfaces.DTOToModel;
import org.tlc.microservices.userservice.model.Customer;

@Setter
@Getter
@Component
@RequiredArgsConstructor
public class CreateCustomerDTO  implements DTOToModel<Customer> {

    private static final ModelMapper modelMapper = new ModelMapper();

    private String email;
    private String password;
    private String username;
    @Nullable private Boolean can_short;

    public Customer convertToEntity() throws RuntimeException {
        return modelMapper.map(this, Customer.class);
    }

}
