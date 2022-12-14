package org.tlc.domain.base.order;

public enum Response {
    VALID_CLIENT(true, "Client is valid"),
    VALID_ORDER(true, "order is valid"),
    INVALID_QUANTITY(false, "user does not have enough of the product in their inventory"),
    UNREASONABLE_PRICE(false, "Price set is unlikely to be accepted by exchange"),
    INSUFFICIENT_FUNDS(false, "Not enough funds in account for this transaction"),
    INVALID_REQUEST(false, "Request received was not valid"),
    ORDER_CANCELLED(true, "Order cancelled successfully"),
    ORDER_NOT_CANCELLED(false, "Order could not be cancelled"),
    ORDER_UPDATED(true, "Order updated successfully"),
    ORDER_NOT_UPDATED(false, "Order could not be updated"),
    USER_SERVICE_UNAVALABLE(false, "Could not reach user service"),
    MD_SERVICE_UNAVALABLE(false, "Could not reach market data service");



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
