package org.tlc.microservices.userservice.exceptions;

public class BadOperationException  extends RuntimeException {

    public BadOperationException() {
        super("Operation not permitted");
    }

    public BadOperationException(String message) {
        super(message);
    }
}
