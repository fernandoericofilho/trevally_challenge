package com.trevally_challenge.infrastructure.exceptions;

public class EmptyMappedColumnsException extends RuntimeException {
    public EmptyMappedColumnsException(String message) { super(message); }
}