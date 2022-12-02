package org.tlc.microservices.marketdataservice.model;

public class RedisMessage {
    private String product;
    private Double price;
    private String side;

    public RedisMessage(String product, Double price, String side) {
        this.product = product;
        this.price = price;
        this.side = side;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "RedisMessage{" +
                "product='" + product + '\'' +
                ", price=" + price +
                ", side='" + side + '\'' +
                '}';
    }
}
