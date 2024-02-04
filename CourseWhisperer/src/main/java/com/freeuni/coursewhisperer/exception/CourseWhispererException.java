package com.freeuni.coursewhisperer.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CourseWhispererException extends RuntimeException {

    private final String errorKey;
    private final String errorDescription;
    private final HttpStatus status;
    private Object errorParameters;

    public CourseWhispererException(String errorKey, String errorDescription, HttpStatus status) {
        super(errorDescription);
        this.errorKey = errorKey;
        this.errorDescription = errorDescription;
        this.status = status;
    }

    public CourseWhispererException(String errorKey, String errorDescription, HttpStatus status, Object errorParameters) {
        super(errorDescription);
        this.errorKey = errorKey;
        this.errorDescription = errorDescription;
        this.status = status;
        this.errorParameters = errorParameters;
    }

}
