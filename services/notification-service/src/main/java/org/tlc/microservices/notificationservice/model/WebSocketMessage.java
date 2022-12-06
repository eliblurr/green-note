package org.tlc.microservices.notificationservice.model;

public class WebSocketMessage {
    private String Ticker;

    public WebSocketMessage(String ticker) {
        Ticker = ticker;
    }

    public String getTicker() {
        return Ticker;
    }

    public void setTicker(String ticker) {
        Ticker = ticker;
    }

    public WebSocketMessage() {
    }

    @Override
    public String toString() {
        return "WebSocketMessage{" +
                "Ticker='" + Ticker + '\'' +
                '}';
    }
}
