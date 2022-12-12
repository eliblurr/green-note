package org.tlc.microservices.marketdataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ExchangeProducts implements Serializable {
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

    public ExchangeProducts() {
    }

    public ExchangeProducts(Double MAX_PRICE_SHIFT, Double LAST_TRADED_PRICE, Double BID_PRICE, int SELL_LIMIT, Double ASK_PRICE, int BUY_LIMIT, String TICKER) {
        this.MAX_PRICE_SHIFT = MAX_PRICE_SHIFT;
        this.LAST_TRADED_PRICE = LAST_TRADED_PRICE;
        this.BID_PRICE = BID_PRICE;
        this.SELL_LIMIT = SELL_LIMIT;
        this.ASK_PRICE = ASK_PRICE;
        this.BUY_LIMIT = BUY_LIMIT;
        this.TICKER = TICKER;
    }

    public Double getMAX_PRICE_SHIFT() {
        return MAX_PRICE_SHIFT;
    }

    public void setMAX_PRICE_SHIFT(Double MAX_PRICE_SHIFT) {
        this.MAX_PRICE_SHIFT = MAX_PRICE_SHIFT;
    }

    public Double getLAST_TRADED_PRICE() {
        return LAST_TRADED_PRICE;
    }

    public void setLAST_TRADED_PRICE(Double LAST_TRADED_PRICE) {
        this.LAST_TRADED_PRICE = LAST_TRADED_PRICE;
    }

    public Double getBID_PRICE() {
        return BID_PRICE;
    }

    public void setBID_PRICE(Double BID_PRICE) {
        this.BID_PRICE = BID_PRICE;
    }

    public int getSELL_LIMIT() {
        return SELL_LIMIT;
    }

    public void setSELL_LIMIT(int SELL_LIMIT) {
        this.SELL_LIMIT = SELL_LIMIT;
    }

    public Double getASK_PRICE() {
        return ASK_PRICE;
    }

    public void setASK_PRICE(Double ASK_PRICE) {
        this.ASK_PRICE = ASK_PRICE;
    }

    public int getBUY_LIMIT() {
        return BUY_LIMIT;
    }

    public void setBUY_LIMIT(int BUY_LIMIT) {
        this.BUY_LIMIT = BUY_LIMIT;
    }

    public String getTICKER() {
        return TICKER;
    }

    public void setTICKER(String TICKER) {
        this.TICKER = TICKER;
    }
}
