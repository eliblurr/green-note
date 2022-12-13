package org.tlc.microservices.loggingservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.logging.enums.Ops;
import org.tlc.microservices.loggingservice.model.Log;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Component
public class CreateLogDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    private String message;
    private UUID user;

    public Log convertToEntity() throws RuntimeException {
        return modelMapper.map(this, Log.class);
    }

    public void setMessage(Ops op, Timestamp time) {
        this.message = String.format(op.getMessage(), time.toString());
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
