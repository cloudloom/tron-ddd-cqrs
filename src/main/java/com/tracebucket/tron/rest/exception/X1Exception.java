package com.tracebucket.tron.rest.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class X1Exception extends RuntimeException implements Serializable {

    private String message;

    private HttpStatus httpStatus;

    public X1Exception(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}