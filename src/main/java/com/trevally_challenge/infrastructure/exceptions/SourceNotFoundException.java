package com.trevally_challenge.infrastructure.exceptions;

public class SourceNotFoundException extends RuntimeException {
    public SourceNotFoundException(String message) { super(message); }
}