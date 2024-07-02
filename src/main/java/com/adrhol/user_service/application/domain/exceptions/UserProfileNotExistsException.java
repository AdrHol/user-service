package com.adrhol.user_service.application.domain.exceptions;

public class UserProfileNotExistsException extends RuntimeException{
    public UserProfileNotExistsException() {
        super("User profile not found.");
    }
}
