package com.trevally_challenge.exceptions;

public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException(String message) { super(message); }
}