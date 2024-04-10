package com.freeuni.coursewhisperer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

@Slf4j
@Primary
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CourseWhispererException.class)
    protected ResponseEntity<Object> handleError(CourseWhispererException ex) {
        log.error("CourseWhispererException: ", ex);
        return new ResponseEntity<>(new CourseWhispererExceptionSchema(ex), ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {
        var exceptionMessage = String.join(",", ex.getAllErrors().stream()
                .map(objectError -> ((FieldError) objectError).getField() + " " + objectError.getDefaultMessage()).toList());

        return new ResponseEntity<>(new CourseWhispererExceptionSchema(HttpStatus.BAD_REQUEST,
                String.join(".", Arrays.asList(exceptionMessage.split(" |,"))),
                exceptionMessage, null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NumberFormatException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    protected ResponseEntity<Object> handleException(Object ex) {
        return new ResponseEntity<>(new CourseWhispererExceptionSchema(HttpStatus.BAD_REQUEST, "invalid.parameters",
                "Invalid parameters", null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleError(MissingServletRequestParameterException ex) {
        log.error("Missing parameter exception: ", ex);
        return new ResponseEntity<>(new CourseWhispererExceptionSchema(HttpStatus.BAD_REQUEST,
                "missing.request-parameter", ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleError(Exception ex) {
        log.error("Exception:  ", ex);
        if (ex.getCause() != null && ex.getCause() instanceof CourseWhispererException wce) {
            return new ResponseEntity<>(new CourseWhispererExceptionSchema(wce), wce.getStatus());
        }
        return new ResponseEntity<>(new CourseWhispererExceptionSchema(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
