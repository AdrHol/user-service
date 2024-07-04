package com.adrhol.user_service.application.domain.service;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.domain.exceptions.ProfileAlreadyInUseException;
import com.adrhol.user_service.application.ports.in.*;
import com.adrhol.user_service.application.ports.out.UserProfileQueryPort;
import com.adrhol.user_service.application.ports.out.UserRegistrationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProfileService implements RegisterUserProfileUseCase, DeactivateUserProfileUseCase, UpdateUserProfileUseCase,
        RetrieveActiveProfilesUseCase, FindUserProfileUseCase, SearchUserProfileQuery{

    private final UserRegistrationPort userRegistrationPort;
    private final UserProfileQueryPort userProfileQueryPort;

    @Override
    public boolean deactivateProfile(String userId) {
        return false;
    }

    @Override
    public DomainUser registerUser(CreateUserCommand createUserCommand) {
        checkIfAccountHasAssignedProfile(createUserCommand);
        return userRegistrationPort.registerUser(createUserCommand);
    }

    @Override
    public DomainUser updateProfile(UpdateUserCommand updateUserCommand) {
        DomainUser userProfile = findProfileById(updateUserCommand.profileId());
        return null;
    }
    @Override
    public DomainUser findProfileById(String id) {
        return userProfileQueryPort.getUserById(id);
    }

    @Override
    public List<DomainUser> findUsersByCriteria(SearchUserProfileQuery searchUserProfileQuery) {
        return null;
    }

    @Override
    public List<DomainUser> getActiveUsers() {
        return userProfileQueryPort.getAllActiveUsers();
    }
    private void checkIfAccountHasAssignedProfile(CreateUserCommand command) throws ProfileAlreadyInUseException{
        if(userProfileQueryPort.getUserByAccountId(command.accountId()).isPresent()){
            throw new ProfileAlreadyInUseException();
        }
    }
}
