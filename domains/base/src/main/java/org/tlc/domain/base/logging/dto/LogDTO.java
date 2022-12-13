package org.tlc.domain.base.logging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {
    private UUID user;
    private String message;
}
