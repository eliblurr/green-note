package org.tlc.microservices.orderservice;

public enum Response {
VALID_ORDER(true, "order is valid"),
INVALID_QUANTITY(false, "user does not have enough of the product in their inventory"),
UNREASONABLE_PRICE(false, "Price set is unlikely to be accepted by exchange"),
INSUFFICIENT_FUNDS(false, "Not enough funds in account for this transaction"),
INVALID_REQUEST(false, "Request received was not valid");
    Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    final boolean success;
    final String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
