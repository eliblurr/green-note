package org.tlc.microservices.userservice.auth.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.tlc.microservices.userservice.dto.customer.CustomerDTO;

@Data
@Component
public class CustomerResponseDTO {
    private String access_token = "initialised token";
    private String refresh_token = "initialised token";
    private CustomerDTO customer;
}
