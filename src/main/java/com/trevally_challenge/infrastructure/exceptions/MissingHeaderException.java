package com.trevally_challenge.infrastructure.exceptions;

public class MissingHeaderException extends RuntimeException {
    public MissingHeaderException(String message) { super(message); }
}