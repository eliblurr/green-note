package org.tlc.microservices.loggingservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.loggingservice.model.Log;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Component
public class LogDTO {

    private static final ModelMapper modelMapper = new ModelMapper();

    private UUID id;
    private String message;
    private Timestamp created;

    public static LogDTO convertToDTO(Log log){
        return modelMapper.map(log, LogDTO.class);
    }

}
