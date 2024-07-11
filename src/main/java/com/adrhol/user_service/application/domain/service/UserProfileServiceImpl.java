package com.adrhol.user_service.application.domain.service;

import com.adrhol.user_service.adapters.out.persistence.mapper.UserMapper;
import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.ports.in.*;
import com.adrhol.user_service.application.ports.out.UserProfileQueryPort;
import com.adrhol.user_service.application.ports.out.UserRegistrationPort;
import com.adrhol.user_service.common.validation.UserProfileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProfileServiceImpl implements RegisterUserProfileUseCase, DeactivateUserProfileUseCase, ProfileDetailsEditionUseCase,
                                               RetrieveActiveProfilesUseCase, FindUserProfileUseCase, SearchUserProfileQuery{

    private final UserRegistrationPort userRegistrationPort;
    private final UserProfileQueryPort userProfileQueryPort;
    private final UserMapper userMapper;
    private final UserProfileValidator userProfileValidator;
    @Override
    public boolean deactivateProfile(String userId) {
        return false;
    }

    @Override
    public UserProfile registerUser(CreateUserCommand createUserCommand) {
        userProfileValidator.throwIfAccountHasAssignedProfile(createUserCommand.accountId());

        UserProfile newUser = userMapper.creationCommandToEntity(createUserCommand);
        return userRegistrationPort.registerUser(newUser);
    }

    @Override
    public UserProfile updateProfile(UpdateUserCommand updateUserCommand) {
        UserProfile userProfile = userProfileQueryPort.getUserById(updateUserCommand.profileId());
        return null;
    }

    @Override
    public UserProfile promoteProfile(final ProfilePromotionCommand profilePromotionCommand) {
        UserProfile userProfile = userProfileQueryPort.getUserById(profilePromotionCommand.profileId());

        return null;
    }

    @Override
    public UserProfile findProfileById(String id) {
        return userProfileQueryPort.getUserById(id);
    }

    @Override
    public List<UserProfile> findUsersByCriteria(SearchUserProfileQuery searchUserProfileQuery) {
        return null;
    }

    @Override
    public List<UserProfile> getActiveUsers() {
        return userProfileQueryPort.getAllActiveUsers();
    }

}
