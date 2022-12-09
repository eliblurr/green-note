package org.tlc.microservices.userservice.auth.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RefreshTokenResponseDTO {
    private String access_token;
    private String refresh_token;
}
