package org.tlc.microservices.loggingservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.loggingservice.model.Log;

@Getter
@Component
public class CreateLogDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    private String message;

    public Log convertToEntity() throws RuntimeException {
        return modelMapper.map(this, Log.class);
    }

    public void setMessage(String message) {
//        read enum based on op
        this.message = message;
    }
}
