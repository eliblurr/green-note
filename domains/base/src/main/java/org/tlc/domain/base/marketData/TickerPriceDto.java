package org.tlc.domain.base.marketData;


public class TickerPriceDto {
    private Double price;
    private int quantity;

    public TickerPriceDto() {
    }

    public TickerPriceDto(Double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TickerPriceDto{" +
                "price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
