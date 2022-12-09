package org.tlc.microservices.userservice.dto.admin;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.dto.interfaces.DTOToModel;
import org.tlc.microservices.userservice.model.Admin;

@Setter
@Getter
@Component
@RequiredArgsConstructor
public class CreateAdminDTO implements DTOToModel<Admin> {
    private static final ModelMapper modelMapper = new ModelMapper();

    private String email;
    private String password;

    public Admin convertToEntity() throws RuntimeException {
        return modelMapper.map(this, Admin.class);
    }

}
