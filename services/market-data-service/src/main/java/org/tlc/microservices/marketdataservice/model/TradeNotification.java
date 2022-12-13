package org.tlc.microservices.marketdataservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data

@RedisHash("NotificationTrade")

public class TradeNotification implements Serializable {
    @Id
    private String TradeId;

    private Double cumulatitivePrice;

    private int cumulatitiveQuantity;

    public TradeNotification() {
    }

    public TradeNotification(String tradeId, Double cumulatitivePrice, int cumulatitiveQuantity) {
        TradeId = tradeId;
        this.cumulatitivePrice = cumulatitivePrice;
        this.cumulatitiveQuantity = cumulatitiveQuantity;
    }

    public String getTradeId() {
        return TradeId;
    }

    public void setTradeId(String tradeId) {
        TradeId = tradeId;
    }

    public Double getCumulatitivePrice() {
        return cumulatitivePrice;
    }

    public void setCumulatitivePrice(Double cumulatitivePrice) {
        this.cumulatitivePrice = cumulatitivePrice;
    }

    public int getCumulatitiveQuantity() {
        return cumulatitiveQuantity;
    }

    public void setCumulatitiveQuantity(int cumulatitiveQuantity) {
        this.cumulatitiveQuantity = cumulatitiveQuantity;
    }

    @Override
    public String toString() {
        return "TradeNotification{" +
                "TradeId='" + TradeId + '\'' +
                ", cumulatitivePrice=" + cumulatitivePrice +
                ", cumulatitiveQuantity=" + cumulatitiveQuantity +
                '}';
    }
}


