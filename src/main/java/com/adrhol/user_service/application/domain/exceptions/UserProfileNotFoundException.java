package com.adrhol.user_service.application.domain.exceptions;

public class UserProfileNotFoundException extends RuntimeException{
    public UserProfileNotFoundException() {
        super("User profile not found.");
    }
}
