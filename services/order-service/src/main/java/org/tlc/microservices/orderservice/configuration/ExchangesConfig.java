package org.tlc.microservices.orderservice.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tlc.domain.base.marketData.ExchangesDTO;
import org.tlc.microservices.orderservice.services.ExchangeDataFetcher;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Data
@Component
public class ExchangesConfig {
    @Autowired
    ExchangeDataFetcher exchangeDataFetcher;
    public String get(String key) {
        return exchangesURL.get(key);
    }

    private HashMap<String, String> exchangesURL = new HashMap<>();

    private HashMap<String, UUID> exchangesUUID = new HashMap<>();

    private List<ExchangesDTO> allExchanges;

    public ExchangesConfig() {
//        this.allExchanges = exchangeDataFetcher.getExchanges();
        initExchanges();
    }

    public UUID getExchangeUUID(String key) {
        return exchangesUUID.get(key);
    }

    public void initExchanges(){
        this.exchangesURL.put("MAL1", "https://exchange.matraining.com/");
        this.exchangesURL.put("MAL2", "https://exchange2.matraining.com/");
//        for(ExchangesDTO exchange : allExchanges){
//            this.exchanges.put(exchange.getExchangeName(), exchange.getExchangeUrl());
//            this.exchangesUUID.put(exchange.getExchangeName(), exchange.getExchangeId());
//        }
    }

}
