package com.trevally_challenge.infrastructure.exceptions;

public class FileExistException extends RuntimeException {
    public FileExistException(String message) { super(message); }
}
