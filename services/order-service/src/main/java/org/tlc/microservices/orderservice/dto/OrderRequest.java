package org.tlc.microservices.orderservice.dto;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Order")
public class OrderRequest {
    private String client;
    private String product;
    private double price;
    private int quantity;
    private int portfolio;
    private String side;
    private String type;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(int portfolio) {
        this.portfolio = portfolio;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Order{" +
                "client=" + client +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", side='" + side + '\'' +
                '}';
    }
}
