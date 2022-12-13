package org.tlc.microservices.userservice.auth.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.dto.admin.AdminDTO;
import org.tlc.microservices.userservice.model.Admin;

@Data
@Component
public class AdminResponseDTO {

    private String access_token = "initialised token";
    private String refresh_token = "initialised token";
    private AdminDTO admin;

}
