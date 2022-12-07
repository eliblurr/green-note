package org.tlc.microservices.marketdataservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class WebHookData {
    @JsonProperty
    private String orderType;
    @JsonProperty
    private String product;
    @JsonProperty
    private String side;
    @JsonProperty
    private String orderID;
    @JsonProperty
    private Double price;
    @JsonProperty
    private int qty;
    @JsonProperty
    private int cumQty;
    @JsonProperty
    private Double cumPrx;
    @JsonProperty
    private String exchange;
    @JsonProperty
    private Timestamp timestamp;

    @Override
    public String toString() {
        return "Transfered Data{" +
                "orderType='" + orderType + '\'' +
                ", product='" + product + '\'' +
                ", side='" + side + '\'' +
                ", orderID='" + orderID + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", cumQty=" + cumQty +
                ", cumPrx=" + cumPrx +
                ", exchange='" + exchange + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
