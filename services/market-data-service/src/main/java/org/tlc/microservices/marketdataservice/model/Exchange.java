package org.tlc.microservices.marketdataservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Exchange")
@Data
public class Exchange implements Serializable {
    @Id
    private String ExchangeName;
    private String ExchangeUrl;

    public Exchange() {
    }

    public Exchange(String exchangeName, String exchangeUrl) {
        ExchangeName = exchangeName;
        ExchangeUrl = exchangeUrl;
    }

    public String getExchangeName() {
        return ExchangeName;
    }

    public void setExchangeName(String exchangeName) {
        ExchangeName = exchangeName;
    }

    public String getExchangeUrl() {
        return ExchangeUrl;
    }

    public void setExchangeUrl(String exchangeUrl) {
        ExchangeUrl = exchangeUrl;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "ExchangeName='" + ExchangeName + '\'' +
                ", ExchangeUrl='" + ExchangeUrl + '\'' +
                '}';
    }
}
