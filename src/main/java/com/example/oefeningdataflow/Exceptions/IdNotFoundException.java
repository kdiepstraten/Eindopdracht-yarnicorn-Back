package com.example.oefeningdataflow.Exceptions;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String message) {
        super(message);
    }

    public IdNotFoundException() {
        super();
    }
}
