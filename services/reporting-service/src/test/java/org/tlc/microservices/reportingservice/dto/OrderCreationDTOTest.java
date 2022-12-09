package org.tlc.microservices.reportingservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlc.domain.base.order.enums.OrderPosition;
import org.tlc.domain.base.order.enums.OrderStatus;
import org.tlc.domain.base.order.enums.Side;
import org.tlc.microservices.reportingservice.model.OrderTrade;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderCreationDTOTest {

    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void whenMapGameWithExactMatch_thenConvertsToDTO() {
        // when similar source object is provided
        OrderCreationDTO orderCreationDTO = new OrderCreationDTO(3,"AAPL",3.2,20,2, Side.BUY, OrderPosition.NORMAL, OrderStatus.ACCEPTED);
        OrderTrade orderTrade = this.mapper.map(orderCreationDTO, OrderTrade.class);

        // then it maps by default
        assertEquals(orderCreationDTO.getQuantity(), orderCreationDTO.getQuantity());
        assertEquals(orderTrade.getClientID(), orderTrade.getClientID());
    }

}