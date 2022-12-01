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

@Component
public class ReadOrderDTO implements DTOToModel<OrderTrade> {
    private static final ModelMapper modelMapper = new ModelMapper();
    private int orderID;
    private int clientID;
    private double price;
    private String ticker;
    private Timestamp created;
    private Timestamp updated;
    private OrderStatus status;
    private int quantity;
    private Side side;
    private OrderType type;
    private int portfolioID;
    private OrderPosition position;

    public OrderTrade convertToEntity() throws RuntimeException {
        return modelMapper.map(this, OrderTrade.class);
    }

    //constructors
    public ReadOrderDTO() {
    }

    public ReadOrderDTO(int orderID, int clientID, double price, String ticker, Timestamp created, Timestamp updated, OrderStatus status, int quantity, Side side, OrderType type, int portfolioID, OrderPosition position) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.price = price;
        this.ticker = ticker;
        this.created = created;
        this.updated = updated;
        this.status = status;
        this.quantity = quantity;
        this.side = side;
        this.type = type;
        this.portfolioID = portfolioID;
        this.position = position;
    }

//getters and setters

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public int getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(int portfolioID) {
        this.portfolioID = portfolioID;
    }

    public OrderPosition getPosition() {
        return position;
    }

    public void setPosition(OrderPosition position) {
        this.position = position;
    }
}
