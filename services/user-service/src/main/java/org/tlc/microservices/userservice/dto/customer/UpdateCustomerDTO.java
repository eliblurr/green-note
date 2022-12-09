package org.tlc.microservices.userservice.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCustomerDTO {

    @Nullable private String username;
    @Nullable private String password;
    @Nullable private Boolean is_active;
    @Nullable private Boolean can_short;

}

