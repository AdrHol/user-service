package com.adrhol.user_service.application.domain.service;

import com.adrhol.user_service.adapters.out.persistance.UserProfileCreationAdapter;
import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import com.adrhol.user_service.application.ports.in.DeactivateUserProfileUseCase;
import com.adrhol.user_service.application.ports.in.RegisterUserProfileUseCase;
import com.adrhol.user_service.application.ports.in.UpdateUserProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService implements RegisterUserProfileUseCase, DeactivateUserProfileUseCase, UpdateUserProfileUseCase {

    private final UserProfileCreationAdapter userProfileCreationAdapter;

    @Override
    public boolean deactivateProfile(String userId) {
        return false;
    }

    @Override
    public DomainUser registerUser(CreateUserCommand createUserCommand) {
        UserProfileMongoEntity user = userProfileCreationAdapter.registerUser(createUserCommand);
        return new DomainUser(user.getId(), user.getFirstName(), user.getLastName());
    }

    @Override
    public DomainUser updateProfile(CreateUserCommand createUserCommand) {
        return null;
    }
}
