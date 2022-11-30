package org.tlc.microservices.userservice.exceptions;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.modelmapper.MappingException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseBody
public class ControllerAdvisor {

    @ExceptionHandler(value = {NotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundHandler(RuntimeException e, WebRequest wr){
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(value = {MappingException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage mappingErrorHandler(RuntimeException e, WebRequest wr){
        return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), "unacceptable request body");
    }

    @ExceptionHandler(value = {PropertyReferenceException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage propertyReferenceHandler(RuntimeException e, WebRequest wr){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

}
