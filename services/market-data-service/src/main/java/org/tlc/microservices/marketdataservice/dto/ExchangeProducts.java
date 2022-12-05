package org.tlc.microservices.marketdataservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeProducts {
    @JsonProperty
    private Double MAX_PRICE_SHIFT;
    @JsonProperty
    private Double LAST_TRADED_PRICE;
    @JsonProperty
    private Double BID_PRICE;
    @JsonProperty
    private int SELL_LIMIT;
    @JsonProperty
    private Double ASK_PRICE;
    @JsonProperty
    private int BUY_LIMIT;
    @JsonProperty
    private String TICKER;
}
