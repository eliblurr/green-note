package org.tlc.domain.base.logging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEventDTO {

    private String message;
    private String status;
    private LogDTO logDTO;

}
