package com.trevally_challenge.infrastructure.exceptions;

public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException(String message) { super(message); }
}