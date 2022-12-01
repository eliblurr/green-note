package org.tlc.microservices.reportingservice.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.reportingservice.mapper.DTOToModel;
import org.tlc.microservices.reportingservice.model.*;
import org.tlc.microservices.reportingservice.model.enums.OrderPosition;
import org.tlc.microservices.reportingservice.model.enums.OrderStatus;
import org.tlc.microservices.reportingservice.model.enums.OrderType;
import org.tlc.microservices.reportingservice.model.enums.Side;

import java.sql.Timestamp;

//@AllArgsConstructor
//@NoArgsConstructor
@Component
//@Setter
//@Getter
public class OrderCreationDTO implements DTOToModel<OrderTrade> {
    private static final ModelMapper modelMapper = new ModelMapper();

    private int clientID;//client name?
    private double price;
    private String ticker;
    private Timestamp created;//necessary?
    private Timestamp updated;//necessary?
    private OrderStatus status;//necessary?
    private int quantity;
    private Side side;
    private OrderType type;
    private String portfolioName; //dependency should be id
    private OrderPosition position;

    public OrderTrade convertToEntity() throws RuntimeException{
        return modelMapper.map(this, OrderTrade.class);
    }
}
