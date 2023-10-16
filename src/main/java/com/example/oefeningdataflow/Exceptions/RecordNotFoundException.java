package com.example.oefeningdataflow.Exceptions;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException() {
        super();
    }
}
