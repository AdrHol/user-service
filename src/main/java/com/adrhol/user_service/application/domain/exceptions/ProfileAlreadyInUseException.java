package com.adrhol.user_service.application.domain.exceptions;

public class ProfileAlreadyInUseException extends RuntimeException {
    public ProfileAlreadyInUseException() {
        super(" Cannot create a new profile. This account is already bound to a profile.");
    }
}
