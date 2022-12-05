package org.tlc.microservices.marketdataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.tlc.microservices.marketdataservice.mapper.ReportingServiceMapper;
import org.tlc.microservices.marketdataservice.model.Ticker;

@Data
@NoArgsConstructor
public class TickerDto implements ReportingServiceMapper {
    @JsonProperty
    private String product;
    @JsonProperty
    private Double price;
    @JsonProperty
    private String side;

    private static final ModelMapper modelMapper = new ModelMapper();

    public TickerDto(ReportingServiceDto reportingServiceDto) {
        this.product = reportingServiceDto.getProduct();
        this.price = reportingServiceDto.getPrice();
        this.side = reportingServiceDto.getSide();
    }

    @Override
    public Ticker convertToEntity() {
        return modelMapper.map(this,Ticker.class);
    }
}
