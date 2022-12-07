package org.tlc.microservices.userservice.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateAdminDTO {

    @Nullable private Boolean is_active;
    @Nullable private String password;

}
