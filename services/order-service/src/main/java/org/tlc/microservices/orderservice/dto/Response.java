package org.tlc.microservices.orderservice.dto;

public class Response {
    final boolean success;
    final String message;

    public Response(boolean success, String reason) {
        this.success = success;
        this.message = reason;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
