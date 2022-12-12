package org.tlc.domain.base.exceptions;
import java.util.UUID;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Object not found");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(UUID id) {
        super("Object with Id "+id.toString()+" not found");
    }

}
