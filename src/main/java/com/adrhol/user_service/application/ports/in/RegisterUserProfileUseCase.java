package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.DomainUser;

public interface RegisterUserProfileUseCase {
    DomainUser registerUser(CreateUserCommand createUserCommand);
}
