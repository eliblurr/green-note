package org.tlc.microservices.userservice.dto.customer;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CustomerDTO {

    private static final ModelMapper modelMapper = new ModelMapper();


}
