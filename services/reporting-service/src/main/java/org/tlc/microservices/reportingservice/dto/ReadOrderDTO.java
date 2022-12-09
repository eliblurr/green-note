package org.tlc.microservices.reportingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderSplit;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.microservices.reportingservice.mapper.DTOToModel;
import org.tlc.microservices.reportingservice.model.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class ReadOrderDTO implements DTOToModel<OrderTrade> {
    private static final ModelMapper modelMapper = new ModelMapper();

    private int orderID;
    private int clientID;
    private double price;
    private String product;
    private LocalDateTime created;
    private LocalDateTime updated;
    private OrderStatus status;
    private int quantity;
    private Side side;
    private OrderSplit type;
    private int portfolioID;
    private OrderPosition position;

    public OrderTrade convertToEntity() throws RuntimeException {
        return modelMapper.map(this, OrderTrade.class);
    }


}
