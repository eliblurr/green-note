package org.tlc.microservices.userservice.auth.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginDTO {

    private String email;
    private String password;

    public void setPassword(String password) {
        this.password = password;  // use hash here
    }

}
