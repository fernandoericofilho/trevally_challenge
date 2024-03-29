package com.trevally_challenge.infrastructure.exceptions;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException(String message) { super(message); }
}
