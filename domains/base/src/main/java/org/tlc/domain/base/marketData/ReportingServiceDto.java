package org.tlc.domain.base.marketData;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReportingServiceDto {
    private String orderType;
    private String product;

    private String side;

    private String orderID;

    private Double price;

    private int qty;

    private String exchange;

    private int cumQty;

    private Double cumPrx;
    private Timestamp timestamp;


    public ReportingServiceDto(String orderType, String product, String side, String orderID, Double price, int qty, String exchange, int cumQty, Double cumPrx, Timestamp timestamp) {
        this.orderType = orderType;
        this.product = product;
        this.side = side;
        this.orderID = orderID;
        this.price = price;
        this.qty = qty;
        this.exchange = exchange;
        this.cumQty = cumQty;
        this.cumPrx = cumPrx;
        this.timestamp = timestamp;
    }

    public ReportingServiceDto() {
    }

    public int getCumQty() {
        return cumQty;
    }

    public void setCumQty(int cumQty) {
        this.cumQty = cumQty;
    }

    public Double getCumPrx() {
        return cumPrx;
    }

    public void setCumPrx(Double cumPrx) {
        this.cumPrx = cumPrx;
    }

    @Override
    public String toString() {
        return "{" +
                "orderType='" + orderType + '\'' +
                ", product='" + product + '\'' +
                ", side='" + side + '\'' +
                ", orderID='" + orderID + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", exchange='" + exchange + '\'' +
                ", cumQty=" + cumQty +
                ", cumPrx=" + cumPrx +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
