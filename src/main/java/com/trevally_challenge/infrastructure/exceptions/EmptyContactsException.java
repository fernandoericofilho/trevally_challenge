package com.trevally_challenge.infrastructure.exceptions;

public class EmptyContactsException extends RuntimeException {
    public EmptyContactsException(String message) { super(message); }
}
