package org.tlc.microservices.marketdataservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Queue;

@Data

@RedisHash("TickerPrice")

public class TickerPrice implements Serializable {
    @Id

    private Long id;

    private String exchange;

    private String TickerName;

    private String side;

    private Double prices;

    public TickerPrice() {
    }

    public TickerPrice(Long id, String exchange, String tickerName, String side, Double prices) {
        this.id = id;
        this.exchange = exchange;
        TickerName = tickerName;
        this.side = side;
        this.prices = prices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getTickerName() {
        return TickerName;
    }

    public void setTickerName(String tickerName) {
        TickerName = tickerName;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Double getPrices() {
        return prices;
    }

    public void setPrices(Double prices) {
        this.prices = prices;
    }
}

