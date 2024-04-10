package com.freeuni.coursewhisperer.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@ToString
public class CourseWhispererExceptionSchema {
    private HttpStatus status;
    private String messageCode;
    private String messageDescription;
    private Object errorParameters;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

    private CourseWhispererExceptionSchema() {
        timestamp = LocalDateTime.now();
    }

    public CourseWhispererExceptionSchema(HttpStatus status, String messageCode, String messageDescription, Object errorParameters) {
        this();
        this.status = status;
        this.messageCode = messageCode;
        this.messageDescription = messageDescription;
        this.errorParameters = errorParameters;
    }

    public CourseWhispererExceptionSchema(CourseWhispererException ex) {
        this(ex.getStatus(), ex.getErrorKey(), ex.getErrorDescription(), ex.getErrorParameters());
    }

    public CourseWhispererExceptionSchema(Exception ex) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, "general.server.error", "general.error.message", null);
    }
}
