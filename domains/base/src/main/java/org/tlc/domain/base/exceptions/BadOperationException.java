package org.tlc.domain.base.exceptions;

public class BadOperationException extends RuntimeException {

    public BadOperationException() {
        super("Operation not permitted");
    }

    public BadOperationException(String message) {
        super(message);
    }
}
