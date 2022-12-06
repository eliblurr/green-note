package org.tlc.microservices.notificationservice.model;

public class WebSocketContent {

    private String content;

    public WebSocketContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WebSocketContent{" +
                "content='" + content + '\'' +
                '}';
    }
}
