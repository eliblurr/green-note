package org.tlc.microservices.orderservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.resource.HttpResource;
import org.tlc.domain.base.order.Response;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ResponseHandler {
    public static ResponseEntity<String> getResponse(Response response) {
        switch (response) {
            case VALID_ORDER -> {
                return new ResponseEntity<>(response.getMessage(), HttpStatus.CREATED);
            }
            case INVALID_QUANTITY, INVALID_REQUEST -> {
                return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
            }
            case UNREASONABLE_PRICE, INSUFFICIENT_FUNDS -> {
                return new ResponseEntity<>(response.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
            case ORDER_CANCELLED, ORDER_UPDATED -> {
                return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
            }
            case ORDER_NOT_CANCELLED, ORDER_NOT_UPDATED -> {
                return new ResponseEntity<>(response.getMessage(), HttpStatus.FORBIDDEN);
            }
            case USER_SERVICE_UNAVALABLE, MD_SERVICE_UNAVALABLE -> {
                return new ResponseEntity<>(response.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
            }
            default -> {
                return new ResponseEntity<>(response.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
