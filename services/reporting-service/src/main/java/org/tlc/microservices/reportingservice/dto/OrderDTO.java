package org.tlc.microservices.reportingservice.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderSplit;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.microservices.reportingservice.mapper.DTOToModel;
import org.tlc.microservices.reportingservice.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements DTOToModel<Order> {
    private static final ModelMapper modelMapper = new ModelMapper();

    private UUID id;
    private UUID exchangeOrderId;
    private UUID customer;
    private String product;
    private double price;
    private int quantity;
    private UUID portfolio;
    private Side side;
    private OrderPosition position;
    private OrderStatus status;
    private OrderSplit split;
    private Timestamp updated;
    private Timestamp created;

    public Order convertToEntity() throws RuntimeException {
        return modelMapper.map(this, Order.class);
    }

}
