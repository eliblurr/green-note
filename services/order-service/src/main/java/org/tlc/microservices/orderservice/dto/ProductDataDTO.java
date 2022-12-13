package org.tlc.microservices.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDataDTO {
    @JsonProperty
    private String TICKER;
    @JsonProperty
    private int SELL_LIMIT;
    @JsonProperty
    private Double LAST_TRADED_PRICE;
    @JsonProperty
    private Double MAX_PRICE_SHIFT;
    @JsonProperty
    private Double ASK_PRICE;
    @JsonProperty
    private int BUY_LIMIT;
    @JsonProperty
    private Double BID_PRICE;

}
