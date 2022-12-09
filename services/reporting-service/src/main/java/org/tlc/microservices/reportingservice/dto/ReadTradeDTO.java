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


import java.sql.Timestamp;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadTradeDTO implements DTOToModel<Trade> {
    private static final ModelMapper modelMapper = new ModelMapper();
    private int tradeID;
    private int orderID;
    private TradeStatus status;
    private int quantity;
    private Side side;
    private int exchangeID;
    private double price;
    private Timestamp created;
    private Timestamp updated;

    @Override
    public Trade convertToEntity() {
        return modelMapper.map(this, Trade.class);
    }


}
