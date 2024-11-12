package com.sece.ece.Exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message)
    {
        super(message);
    }
}
