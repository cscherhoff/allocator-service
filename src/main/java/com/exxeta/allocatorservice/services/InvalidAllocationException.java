package com.exxeta.allocatorservice.services;

public class InvalidAllocationException extends Throwable {
    public InvalidAllocationException(String message) {
        super(message);
    }
}
