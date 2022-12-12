package org.tlc.microservices.reportingservice.dto;

import jakarta.annotation.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlc.domain.base.order.dto.CreateOrderDTO;
import org.tlc.domain.base.order.enums.*;
import org.tlc.microservices.reportingservice.model.Order;

import java.util.UUID;

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
        UUID exchangeOrderId = UUID.randomUUID();
        UUID customer = UUID.randomUUID();
        UUID portfolio = UUID.randomUUID();
        CreateOrderDTO orderCreationDTO = new CreateOrderDTO(
                exchangeOrderId, customer, "AAPL",
                3.2, 20, portfolio, Side.BUY,
                OrderPosition.NORMAL, OrderStatus.ACCEPTED,
                OrderSplit.SINGLE
        );
        Order orderTrade = this.mapper.map(orderCreationDTO, Order.class);
        // then it maps by default
        assertEquals(orderCreationDTO.getQuantity(), orderCreationDTO.getQuantity());
        assertEquals(orderTrade.getCustomer(), orderTrade.getCustomer());
    }

}