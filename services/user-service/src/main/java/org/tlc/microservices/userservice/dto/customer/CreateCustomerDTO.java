package org.tlc.microservices.userservice.dto.customer;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.dto.interfaces.DTOToModel;
import org.tlc.microservices.userservice.model.Customer;
import org.tlc.microservices.userservice.model.Portfolio;

import java.util.ArrayList;
import java.util.List;

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
    @Nullable private List<Portfolio> portfolios = new ArrayList<>();

    public Customer convertToEntity() throws RuntimeException {
        return modelMapper.map(this, Customer.class);
    }

}
