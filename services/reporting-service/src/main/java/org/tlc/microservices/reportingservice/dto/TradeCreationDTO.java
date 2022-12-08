package org.tlc.microservices.reportingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.domain.base.order.enums.TradeStatus;
import org.tlc.microservices.reportingservice.mapper.DTOToModel;
import org.tlc.microservices.reportingservice.model.Trade;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TradeCreationDTO implements DTOToModel<Trade> {
    private static final ModelMapper modelMapper = new ModelMapper();
    private int orderID;
    private TradeStatus status;
    private int quantity;
    private Side side;
    private int exchangeID;
    private double price;
    private LocalDateTime created;
    private LocalDateTime updated;

    @Override
    public Trade convertToEntity() throws RuntimeException {
        return modelMapper.map(this, Trade.class);
    }




}
