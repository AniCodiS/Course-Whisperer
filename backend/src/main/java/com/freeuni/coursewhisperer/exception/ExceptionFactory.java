package com.freeuni.coursewhisperer.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public final class ExceptionFactory {
    private ExceptionFactory() {
    }

    public static CourseWhispererException GPACoursesEmpty() {
        return badRequest("gpa.courses.empty", "Courses list is empty");
    }

    public static CourseWhispererException NoLecturersPresent() {
        return badRequest("lecturers.empty", "There are no lecturers present");
    }

    public static CourseWhispererException LecturerNotFound() {
        return notFound("lecturer.not.found", "Lecturer not found");
    }

    public static CourseWhispererException LecturerAlreadyExists() {
        return conflict("lecturer.already.exists", "Lecturer with this email already exists");
    }

    public static CourseWhispererException commentIsNotYours() {
        return badRequest("comment.is.not.yours", "Cannot update comment that is not done by you");
    }

    public static CourseWhispererException containsOddWords() {
        return badRequest("contains.odd.word",
                "The text you provided contains words that goes against our guidelines");
    }

    public static CourseWhispererException resourceNotFound(Class<?> clazz, String id) {
        return notFound("resource.not.found",
                String.format("%s with id %s not found", clazz.getSimpleName(), id));
    }

    //--------------------------------------------------------------------------------------------------------------

    private static CourseWhispererException notFound(String key, String message) {
        return createException(key, message, HttpStatus.NOT_FOUND);
    }

    private static CourseWhispererException badRequest(String key, String message) {
        return createException(key, message, HttpStatus.BAD_REQUEST);
    }

    private static CourseWhispererException unauthorized(String key, String message) {
        return createException(key, message, HttpStatus.UNAUTHORIZED);
    }

    private static CourseWhispererException internalServer(String key, String message) {
        return createException(key, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static CourseWhispererException rateLimit(String key, String message) {
        return createException(key, message, HttpStatus.TOO_MANY_REQUESTS);
    }

    private static CourseWhispererException forbidden(String key, String message) {
        return createException(key, message, HttpStatus.FORBIDDEN);
    }

    private static CourseWhispererException conflict(String key, String message) {
        return createException(key, message, HttpStatus.CONFLICT);
    }

    private static CourseWhispererException createException(String key, String message, HttpStatus status) {
        return createException(key, message, status, (String[]) null);
    }

    private static CourseWhispererException createException(String key, String message, HttpStatus status, String... params) {
        Map<String, String> transParamsMap = null;
        if (params != null && params.length > 0) {
            transParamsMap = new HashMap<>();
            for (int i = 0; i < params.length; i++) {
                transParamsMap.put(params[i], params[++i]);
            }
        }
        return new CourseWhispererException("course.whisperer" + key, message, status, transParamsMap);
    }

}
