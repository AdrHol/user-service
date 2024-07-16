package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.UserProfile;

public interface DeactivateUserProfileUseCase {

    boolean removeProfile(String userId);
    UserProfile removePremium(String profileId);
}
