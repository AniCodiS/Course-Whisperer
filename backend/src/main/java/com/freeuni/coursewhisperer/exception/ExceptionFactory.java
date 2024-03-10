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

    public static CourseWhispererException NoPersonalInformationPresent() {
        return notFound("personal.information.not.found", "Personal information not found");
    }

    public static CourseWhispererException PersonalInformationNotFound() {
        return notFound("personal.information.not.found", "Personal information not found");
    }

    public static CourseWhispererException UserNotFound() {
        return notFound("user.not.found", "User not found");
    }

    public static CourseWhispererException PersonalInformationAlreadyExists() {
        return conflict("personal.information.already.exists", "Personal information with this email already exists");
    }

    public static CourseWhispererException NoStudyGroupsPresent() {
        return notFound("study.groups.not.found", "There are no study groups present");
    }

    public static CourseWhispererException StudyGroupNotFound() {
        return notFound("study.group.not.found", "Study group not found");
    }

    public static CourseWhispererException StudyGroupAlreadyExists() {
        return conflict("study.group.already.exists", "Study group with this name already exists");
    }

    public static CourseWhispererException StudyGroupNotYours() {
        return forbidden("study.group.not.yours", "You are not a member of this study group");
    }

    public static CourseWhispererException NoStudyGroupMembersPresent() {
        return notFound("study.group.members.not.found", "There are no study group members present");
    }

    public static CourseWhispererException StudyGroupWithNameNotFound() {
        return notFound("study.group.with.name.not.found", "Study group with this name not found");
    }

    public static CourseWhispererException StudyGroupIsFull() {
        return conflict("study.group.is.full", "Study group is full");
    }

    public static CourseWhispererException NoUsersPresent() {
        return notFound("users.not.found", "There are no users present");
    }

    public static CourseWhispererException UsernameAlreadyExists() {
        return conflict("username.already.exists", "User with this username already exists");
    }

    public static CourseWhispererException EmailAlreadyExists() {
        return conflict("email.already.exists", "User with this email already exists");
    }

    public static CourseWhispererException PasswordsDoNotMatch() {
        return badRequest("passwords.do.not.match", "Passwords do not match");
    }

    public static CourseWhispererException OldPasswordDoesNotMatch() {
        return badRequest("old.password.does.not.match", "Old password does not match");
    }

    public static CourseWhispererException NoUserScoresPresent() {
        return notFound("user.scores.not.found", "There are no user scores present");
    }

    public static CourseWhispererException UserScoreNotFound() {
        return notFound("user.score.not.found", "User score not found");
    }

    public static CourseWhispererException UserScoreAlreadyExists() {
        return conflict("user.score.already.exists", "User score with this id already exists");
    }

    public static CourseWhispererException commentIsNotYours() {
        return badRequest("comment.is.not.yours", "Cannot update comment that is not done by you");
    }

    public static CourseWhispererException resourseIsNotYours() {
        return badRequest("resource.is.not.yours", "You are trying to access to the resource that does not belong to you");
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
