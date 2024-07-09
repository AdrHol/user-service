package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.UserProfile;

public interface RegisterUserProfileUseCase {
    UserProfile registerUser(CreateUserCommand createUserCommand);
}
