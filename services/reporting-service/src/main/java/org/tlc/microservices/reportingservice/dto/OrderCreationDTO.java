package org.tlc.microservices.reportingservice.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.reportingservice.mapper.DTOToModel;
import org.tlc.microservices.reportingservice.model.*;

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
    private Timestamp created;
    private Timestamp updated;
    private OrderStatus status;
    private int quantity;
    private Side side;
    private OrderType type;
    private String portfolioName; //dependency
    private OrderPosition position;

    public OrderTrade convertToEntity() throws RuntimeException{
        return modelMapper.map(this, OrderTrade.class);
    }
}
