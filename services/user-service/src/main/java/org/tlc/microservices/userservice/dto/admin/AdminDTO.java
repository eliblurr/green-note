package org.tlc.microservices.userservice.dto.admin;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.model.Admin;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Component
public class AdminDTO {

    @Autowired
    private static ApplicationContext context;

    private static final ModelMapper modelMapper = new ModelMapper();

    private UUID id;
    private String email;
    private Timestamp updated;
    private Timestamp created;
    private Boolean is_active;

    public static AdminDTO convertToDTO(Admin admin){

//        System.out.println("\n\n"+context.getBean(ModelMapper.class)+"\n\n");

        return modelMapper.map(admin, AdminDTO.class);
    }

}


