package com.adrhol.user_service.application.ports.in;

public interface DeactivateUserProfileUseCase {

    boolean deactivateProfile(String userId);
}
