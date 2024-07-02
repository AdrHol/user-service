package com.adrhol.user_service.application.domain.service;

import com.adrhol.user_service.adapters.out.persistence.UserMapper;
import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import com.adrhol.user_service.application.ports.in.DeactivateUserProfileUseCase;
import com.adrhol.user_service.application.ports.in.RegisterUserProfileUseCase;
import com.adrhol.user_service.application.ports.in.UpdateUserProfileUseCase;
import com.adrhol.user_service.application.ports.out.UserRegistrationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRegistrationService implements RegisterUserProfileUseCase, DeactivateUserProfileUseCase, UpdateUserProfileUseCase {

    private final UserRegistrationPort userRegistrationPort;

    @Override
    public boolean deactivateProfile(String userId) {
        return false;
    }

    @Override
    public DomainUser registerUser(CreateUserCommand createUserCommand) {
        return userRegistrationPort.registerUser(createUserCommand);
    }

    @Override
    public DomainUser updateProfile(CreateUserCommand createUserCommand) {
        return null;
    }
}
