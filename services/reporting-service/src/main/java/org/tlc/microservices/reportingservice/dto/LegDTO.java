package org.tlc.microservices.reportingservice.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.enums.LegStatus;
import org.tlc.microservices.reportingservice.mapper.DTOToModel;
import org.tlc.microservices.reportingservice.model.Leg;
import org.tlc.microservices.reportingservice.model.Order;


import java.sql.Timestamp;
import java.util.UUID;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LegDTO implements DTOToModel<Leg> {
    private static final ModelMapper modelMapper = new ModelMapper();

    private UUID id;
    private LegStatus status;
    private int quantity;
    private Side side;
    private UUID exchangeId;
    private double price;
    private Timestamp updated;
    private Timestamp created;

    @Override
    public Leg convertToEntity() {
        return modelMapper.map(this, Leg.class);
    }

}
