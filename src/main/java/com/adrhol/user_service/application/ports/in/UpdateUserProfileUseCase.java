package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.UserProfile;

public interface UpdateUserProfileUseCase {

    UserProfile updateProfile(UpdateUserCommand updateUserCommand);
}
