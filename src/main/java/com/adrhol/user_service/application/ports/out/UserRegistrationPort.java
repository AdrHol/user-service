package com.adrhol.user_service.application.ports.out;

import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import com.adrhol.user_service.application.ports.in.UpdateUserCommand;

public interface UserRegistrationPort {
    UserProfile registerUser(CreateUserCommand user);
    UserProfile updateUser(UpdateUserCommand updateUserCommand);
}
