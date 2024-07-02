package com.adrhol.user_service.application.ports.out;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;

public interface UserRegistrationPort {
    DomainUser registerUser(CreateUserCommand user);
}
