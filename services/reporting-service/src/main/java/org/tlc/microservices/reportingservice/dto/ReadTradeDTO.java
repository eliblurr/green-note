package org.tlc.microservices.reportingservice.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.reportingservice.mapper.DTOToModel;
import org.tlc.microservices.reportingservice.model.enums.Side;
import org.tlc.microservices.reportingservice.model.Trade;
import org.tlc.microservices.reportingservice.model.enums.TradeStatus;

import java.sql.Timestamp;

@Component
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

    //Constructors
    public ReadTradeDTO(){}

    public ReadTradeDTO(int tradeID, int orderID, TradeStatus status, int quantity, Side side, int exchangeID, double price, Timestamp created, Timestamp updated) {
        this.tradeID = tradeID;
        this.orderID = orderID;
        this.status = status;
        this.quantity = quantity;
        this.side = side;
        this.exchangeID = exchangeID;
        this.price = price;
        this.created = created;
        this.updated = updated;
    }

    //Getters and Setters

    public int getTradeID() {
        return tradeID;
    }

    public void setTradeID(int tradeID) {
        this.tradeID = tradeID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public TradeStatus getStatus() {
        return status;
    }

    public void setStatus(TradeStatus status) {
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

    public int getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(int exchangeID) {
        this.exchangeID = exchangeID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
