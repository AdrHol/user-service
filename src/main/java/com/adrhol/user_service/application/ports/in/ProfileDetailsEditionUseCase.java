package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.UserProfile;

public interface ProfileDetailsEditionUseCase {
    UserProfile updateProfile(UpdateUserCommand updateUserCommand);
    UserProfile promoteProfile(ProfilePromotionCommand profilePromotionCommand);
}
