package org.tlc.microservices.reportingservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.tlc.microservices.reportingservice.dto.OrderCreationDTO;
import org.tlc.microservices.reportingservice.model.enums.OrderPosition;
import org.tlc.microservices.reportingservice.model.enums.OrderStatus;
import org.tlc.microservices.reportingservice.model.enums.OrderSplit;
import org.tlc.microservices.reportingservice.model.enums.Side;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class OrderTrade {
    private static final ModelMapper modelMapper = new ModelMapper();//unneccessary?

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long orderID;
    private int clientID;
    private String product;
    private double price;
    private int quantity;
    private int portfolioID; //dependency
    private Side side;
    private OrderPosition position;
    private OrderStatus status;
    private OrderSplit split;
//    @Column(columnDefinition = "CURRENT_TIMESTAMP")
    private LocalDateTime created;
    private LocalDateTime updated;

    //Constructors
    public OrderTrade(OrderCreationDTO orderCreationDTO){
        this.clientID = orderCreationDTO.getClientID();
        this.product = orderCreationDTO.getProduct();
        this.price = orderCreationDTO.getPrice();
        this.quantity = orderCreationDTO.getQuantity();
        this.portfolioID = orderCreationDTO.getPortfolioID();
        this.side=orderCreationDTO.getSide();
        this.position = orderCreationDTO.getPosition();
        this.status = orderCreationDTO.getStatus();
        this.created = LocalDateTime.now();
    }

    //Setters and getters

}
