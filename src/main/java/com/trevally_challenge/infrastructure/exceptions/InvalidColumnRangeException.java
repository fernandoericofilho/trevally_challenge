package com.trevally_challenge.infrastructure.exceptions;

public class InvalidColumnRangeException extends RuntimeException {
    public InvalidColumnRangeException(String message) { super(message); }
}