package org.tlc.microservices.userservice.exceptions;

import org.modelmapper.MappingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.tlc.domain.base.exceptions.BadCredentialsException;

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

    @ExceptionHandler(value = {InternalServerErrorException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage jsonProcessingHandler(RuntimeException e, WebRequest wr){
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(value = {BadOperationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badOperationHandler(RuntimeException e, WebRequest wr){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage daoConflictHandler(RuntimeException e, WebRequest wr){
        return new ErrorMessage(HttpStatus.CONFLICT.value(), "unique object already exists -> "+e.getMessage());
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class, HttpMessageNotReadableException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage invalidArgumentHandler(RuntimeException e, WebRequest wr){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage invalidArgumentTypeHandler(RuntimeException e, WebRequest wr){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage badCredentialsTypeHandler(RuntimeException e, WebRequest wr){
        return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }
}
