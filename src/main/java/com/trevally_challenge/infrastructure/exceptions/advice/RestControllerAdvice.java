package com.trevally_challenge.infrastructure.exceptions.advice;

import com.trevally_challenge.infrastructure.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProcessingFileEsception.class)
    private ResponseEntity<RestErrorMessage> processingFileHandler(ProcessingFileEsception exception) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(FileExistException.class)
    private ResponseEntity<RestErrorMessage> fileExistHandler(FileExistException exception) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(FileExtensionException.class)
    private ResponseEntity<RestErrorMessage> fileExtensionHandler(FileExtensionException exception) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(MissingHeaderException.class)
    private ResponseEntity<RestErrorMessage> missingHeaderHandler(MissingHeaderException exception) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(InvalidDelimiterException.class)
    private ResponseEntity<RestErrorMessage> invalidDelimiterHandler(InvalidDelimiterException exception) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(IndexOutOfRangeException.class)
    private ResponseEntity<RestErrorMessage> indexOutOfRangeHandler(IndexOutOfRangeException exception) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(EmptyContactsException.class)
    private ResponseEntity<RestErrorMessage> emptyContactsHandler(EmptyContactsException exception) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private ResponseEntity<RestErrorMessage> buildResponse(HttpStatus status, String message) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(status, message);
        return ResponseEntity.status(status).body(restErrorMessage);
    }
}

