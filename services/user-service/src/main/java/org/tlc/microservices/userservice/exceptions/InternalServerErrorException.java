package org.tlc.microservices.userservice.exceptions;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException() {
        super("Something went wrong");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

}
