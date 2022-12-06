package org.tlc.microservices.marketdataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.marketdataservice.mapper.ReportingServiceMapper;
import org.tlc.microservices.marketdataservice.model.Ticker;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TickerDto implements ReportingServiceMapper {
    @JsonProperty
    private String product;
    @JsonProperty
    private Double price;
    @JsonProperty
    private String side;

    private static final ModelMapper modelMapper = new ModelMapper();

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public Ticker convertToEntity() {
        return modelMapper.map(this,Ticker.class);
    }
}
