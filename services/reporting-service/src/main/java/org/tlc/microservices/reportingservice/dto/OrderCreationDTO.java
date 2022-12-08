package org.tlc.microservices.reportingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.microservices.reportingservice.mapper.DTOToModel;
import org.tlc.microservices.reportingservice.model.*;

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
