package com.example.bitmaxCRUD.customException;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
