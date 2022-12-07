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

    public ReportingServiceDto(String orderType, String product, String side, String orderID, Double price, int qty, String exchange, Timestamp timestamp) {
        this.orderType = orderType;
        this.product = product;
        this.side = side;
        this.orderID = orderID;
        this.price = price;
        this.qty = qty;
        this.exchange = exchange;
        this.timestamp = timestamp;
    }

    public ReportingServiceDto() {
    }

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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public Object convertToEntity() throws RuntimeException{
        return modelMapper.map(this, WebHookData.class);
    }
}
