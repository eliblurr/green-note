package org.tlc.microservices.reportingservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.reportingservice.mapper.DTOToModel;
import org.tlc.microservices.reportingservice.model.*;
import org.tlc.microservices.reportingservice.model.enums.OrderPosition;
import org.tlc.microservices.reportingservice.model.enums.OrderStatus;
import org.tlc.microservices.reportingservice.model.enums.Side;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Setter
@Getter
public class OrderCreationDTO implements DTOToModel<OrderTrade> {
    private static final ModelMapper modelMapper = new ModelMapper();

    private int clientID;
    private String product;
    private double price;
    private int quantity;
    private int portfolioID; //dependency should be id
    private Side side;
    private OrderPosition position;
    private OrderStatus status;//necessary?

    public OrderTrade convertToEntity() throws RuntimeException{
        return modelMapper.map(this, OrderTrade.class);
    }
}
