package org.tlc.microservices.marketdataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ExchangeProducts implements Serializable {
    @JsonProperty("MAX_PRICE_SHIFT")
    private Double maxPriceShift;
    @JsonProperty("LAST_TRADED_PRICE")
    private Double lastTradedPrice;
    @JsonProperty("BID_PRICE")
    private Double bidPrice;
    @JsonProperty("SELL_LIMIT")
    private int sellLimit;
    @JsonProperty("ASK_PRICE")
    private Double askPrice;
    @JsonProperty("BUY_LIMIT")
    private int buyLimit;
    @JsonProperty("TICKER")
    private String ticker;

    public ExchangeProducts() {
    }

    public ExchangeProducts(Double MAX_PRICE_SHIFT, Double LAST_TRADED_PRICE, Double BID_PRICE, int SELL_LIMIT, Double ASK_PRICE, int BUY_LIMIT, String TICKER) {
        this.maxPriceShift = MAX_PRICE_SHIFT;
        this.lastTradedPrice = LAST_TRADED_PRICE;
        this.bidPrice = BID_PRICE;
        this.sellLimit = SELL_LIMIT;
        this.askPrice = ASK_PRICE;
        this.buyLimit = BUY_LIMIT;
        this.ticker = TICKER;
    }

    public Double getMaxPriceShift() {
        return maxPriceShift;
    }

    public void setMaxPriceShift(Double maxPriceShift) {
        this.maxPriceShift = maxPriceShift;
    }

    public Double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setLastTradedPrice(Double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public int getSellLimit() {
        return sellLimit;
    }

    public void setSellLimit(int sellLimit) {
        this.sellLimit = sellLimit;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    public int getBuyLimit() {
        return buyLimit;
    }

    public void setBuyLimit(int buyLimit) {
        this.buyLimit = buyLimit;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "ExchangeProducts{" +
                "MAX_PRICE_SHIFT=" + maxPriceShift +
                ", LAST_TRADED_PRICE=" + lastTradedPrice +
                ", BID_PRICE=" + bidPrice +
                ", SELL_LIMIT=" + sellLimit +
                ", ASK_PRICE=" + askPrice +
                ", BUY_LIMIT=" + buyLimit +
                ", TICKER='" + ticker + '\'' +
                '}';
    }
}
