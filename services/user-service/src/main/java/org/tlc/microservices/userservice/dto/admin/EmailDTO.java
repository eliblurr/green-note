package org.tlc.microservices.userservice.dto.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EmailDTO {
    private String email;
}
