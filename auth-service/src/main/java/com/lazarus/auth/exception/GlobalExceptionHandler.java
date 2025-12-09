package com.lazarus.auth.exception;

import com.lazarus.auth.dto.common.ErrorResponse;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
        MethodArgumentNotValidException ex
    ) {
        String errors = ex
            .getBindingResult()
            .getAllErrors()
            .stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.joining("; "));

        ErrorResponse response = new ErrorResponse(
            "VALIDATION_ERROR",
            errors,
            OffsetDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
        ResourceNotFoundException ex
    ) {
        ErrorResponse response = new ErrorResponse(
            "NOT_FOUND",
            ex.getMessage(),
            OffsetDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(
        UnauthorizedException ex
    ) {
        ErrorResponse response = new ErrorResponse(
            "UNAUTHORIZED",
            ex.getMessage(),
            OffsetDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResource(
        DuplicateResourceException ex
    ) {
        ErrorResponse response = new ErrorResponse(
            "DUPLICATE_RESOURCE",
            ex.getMessage(),
            OffsetDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {
        // Ignore favicon.ico and actuator requests (browser/monitoring tools)
        if (
            ex instanceof
                org.springframework.web.servlet.resource.NoResourceFoundException
        ) {
            org.springframework.web.servlet.resource.NoResourceFoundException nrfEx =
                (org.springframework.web.servlet.resource.NoResourceFoundException) ex;
            String message = nrfEx.getMessage();
            if (
                message != null &&
                (message.contains("favicon.ico") ||
                    message.contains("actuator"))
            ) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }

        log.error("Unexpected error: ", ex);
        ErrorResponse response = new ErrorResponse(
            "UNEXPECTED_ERROR",
            "Unexpected error occurred",
            OffsetDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            response
        );
    }
}
