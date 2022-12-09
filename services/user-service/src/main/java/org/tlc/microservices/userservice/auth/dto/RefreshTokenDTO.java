package org.tlc.microservices.userservice.auth.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RefreshTokenDTO {
    private String refresh_token;
}
