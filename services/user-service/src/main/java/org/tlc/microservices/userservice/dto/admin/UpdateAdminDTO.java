package org.tlc.microservices.userservice.dto.admin;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.model.Admin;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@Component
public class UpdateAdminDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Nullable
    private Boolean is_active;
    @Nullable
    private String password;

    public Admin convertToEntity() throws RuntimeException {
        return modelMapper.map(this, Admin.class);
    }

}
