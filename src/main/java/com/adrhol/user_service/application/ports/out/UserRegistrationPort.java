package com.adrhol.user_service.application.ports.out;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import com.adrhol.user_service.application.ports.in.UpdateUserCommand;

public interface UserRegistrationPort {
    DomainUser registerUser(CreateUserCommand user);
    DomainUser updateUser(UpdateUserCommand updateUserCommand);
}
