package org.tlc.microservices.marketdataservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Ticker")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ticker {
    @Id
    private String product;
    private Double price;
    private String side;

}
