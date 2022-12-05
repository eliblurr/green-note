package org.tlc.microservices.marketdataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.tlc.microservices.marketdataservice.mapper.ReportingServiceMapper;
import org.tlc.microservices.marketdataservice.model.WebHookData;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportingServiceDto implements ReportingServiceMapper {
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
    private String exchange;
    @JsonProperty
    private Timestamp timestamp;

    private static final ModelMapper modelMapper = new ModelMapper();
    @Override
    public String toString() {
        return "Transfered Data{" +
                "orderType='" + orderType + '\'' +
                ", product='" + product + '\'' +
                ", side='" + side + '\'' +
                ", orderID='" + orderID + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", exchange='" + exchange + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public Object convertToEntity() throws RuntimeException{
        return modelMapper.map(this, WebHookData.class);
    }
}
