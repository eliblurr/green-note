package org.tlc.microservices.marketdataservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticker {
    @Id
    private String product;
    private Double price;
    private String side;

    @Override
    public String toString() {
        return "Ticker{" +
                "product='" + product + '\'' +
                ", price=" + price +
                ", side='" + side + '\'' +
                '}';
    }
}
