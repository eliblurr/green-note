package org.tlc.microservices.loggingservice.enums;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

public enum Ops {
    LOGIN("logged in at %s"),
    CREATEORDER("created order at %s"),
    UPDATEDORDER("updated order at %s"),
    DELETEDORDER("deleted order at %s");

    private String message;
    private Ops(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}
