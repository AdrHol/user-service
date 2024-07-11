package com.adrhol.user_service.application.ports.out;

import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import com.adrhol.user_service.application.ports.in.UpdateUserCommand;

public interface UserRegistrationPort {
    UserProfile registerUser(UserProfile user);
    UserProfile updateUser(UserProfile updateUserCommand);
    void removeProfile(UserProfile userProfile);
}
