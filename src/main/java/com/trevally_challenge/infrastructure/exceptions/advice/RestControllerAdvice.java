package com.trevally_challenge.infrastructure.exceptions.advice;

import com.trevally_challenge.business.util.ErrorMessages;
import com.trevally_challenge.infrastructure.exceptions.EmptyFileException;
import com.trevally_challenge.infrastructure.exceptions.FileProcessingException;
import com.trevally_challenge.infrastructure.exceptions.InvalidDelimiterException;
import com.trevally_challenge.infrastructure.exceptions.MissingHeaderException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    private final ErrorMessages errorMessages;

    @ExceptionHandler(EmptyFileException.class)
    private ResponseEntity<RestErrorMessage> emptyFileHandler(EmptyFileException exception) {
        return buildResponse(HttpStatus.NOT_FOUND, errorMessages.getEmptyFileError());
    }

    @ExceptionHandler(MissingHeaderException.class)
    private ResponseEntity<RestErrorMessage> missingHeaderHandler(MissingHeaderException exception) {
        return buildResponse(HttpStatus.BAD_REQUEST, errorMessages.getMissingHeaderError());
    }

    @ExceptionHandler(InvalidDelimiterException.class)
    private ResponseEntity<RestErrorMessage> invalidDelimiterHandler(InvalidDelimiterException exception) {
        return buildResponse(HttpStatus.BAD_REQUEST, errorMessages.getInvalidDelimiterError());
    }

    @ExceptionHandler(FileProcessingException.class)
    private ResponseEntity<RestErrorMessage> fileProcessingHandler(FileProcessingException exception) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessages.getProcessingFileError() + exception.getMessage());
    }

    private ResponseEntity<RestErrorMessage> buildResponse(HttpStatus status, String message) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(status, message);
        return ResponseEntity.status(status).body(restErrorMessage);
    }
}

