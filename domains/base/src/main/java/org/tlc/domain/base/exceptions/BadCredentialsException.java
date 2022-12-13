package org.tlc.domain.base.exceptions;

public class BadCredentialsException  extends RuntimeException {
    public BadCredentialsException() {
        super("No matching credentials provided");
    }

    public BadCredentialsException(String message) {
        super(message);
    }
}
