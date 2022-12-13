package org.tlc.microservices.orderservice.configuration;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Data
@Component
public class ExchangesConfig implements Exchanges {
    public String get(String key) {
        return exchanges.get(key);
    }

    private HashMap<String, String> exchanges = new HashMap<>();

    public ExchangesConfig() {
        this.exchanges.put("MAL1", "https://exchange.matraining.com/");
        this.exchanges.put("MAL2", "https://exchange2.matraining.com/");
    }

    public UUID getExchangeUUID(String key){
        return UUID.randomUUID();
    }


}
