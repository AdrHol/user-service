package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.DomainUser;

import java.util.Optional;

public interface UpdateUserProfileUseCase {

    DomainUser updateProfile(UpdateUserCommand updateUserCommand);
}
