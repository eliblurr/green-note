package org.tlc.microservices.userservice.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.userservice.model.Admin;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCustomerDTO {

    @Nullable private String username;
    @Nullable private String password;
    @Nullable private Boolean is_active;
    @Nullable private Boolean can_short;

}

