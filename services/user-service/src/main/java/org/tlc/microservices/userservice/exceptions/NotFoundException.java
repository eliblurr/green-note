package org.tlc.microservices.userservice.exceptions;
import java.util.UUID;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Object not found");
    }

    public NotFoundException(UUID id) {
        super("Object with Id "+id.toString()+" not found");
    }

}
