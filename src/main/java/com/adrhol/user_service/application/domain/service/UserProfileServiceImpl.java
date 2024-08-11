package com.adrhol.user_service.application.domain.service;

import com.adrhol.user_service.adapters.out.persistence.mapper.UserMapper;
import com.adrhol.user_service.application.domain.entity.ProfileType;
import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.domain.exceptions.UserProfilePromotionException;
import com.adrhol.user_service.application.ports.in.*;
import com.adrhol.user_service.application.ports.out.UserProfileQueryPort;
import com.adrhol.user_service.application.ports.out.UserRegistrationPort;
import com.adrhol.user_service.common.validation.UserProfileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public boolean removeProfile(String userId) {
        UserProfile profileToDeletion = userProfileQueryPort.getProfileById(userId);
        userRegistrationPort.removeProfile(profileToDeletion);
        return true;
    }

    @Override
    public UserProfile removePremium(final String profileId) {
        UserProfile retrievedProfile = userProfileQueryPort.getProfileById(profileId);
        retrievedProfile.setProfileType(ProfileType.BASIC);
        return userRegistrationPort.updateUser(retrievedProfile);
    }

    @Override
    public UserProfile registerUser(CreateUserCommand createUserCommand) {
        userProfileValidator.throwIfAccountHasAssignedProfile(createUserCommand.accountId());

        UserProfile newUser = userMapper.creationCommandToEntity(createUserCommand);
        return userRegistrationPort.registerUser(newUser);
    }

    @Override
    public UserProfile updateProfile(UpdateUserCommand updateUserCommand) {
        UserProfile userProfile = userProfileQueryPort.getProfileById(updateUserCommand.profileId());
        return null;
    }

    @Override
    public UserProfile promoteProfile(final ProfilePromotionCommand profilePromotionCommand) {
        UserProfile userProfile = userProfileQueryPort.getProfileById(profilePromotionCommand.profileId());

        if(userProfileValidator.canBePromoted(userProfile, profilePromotionCommand.promotionType())){
            return persistPromotedProfile(userProfile, profilePromotionCommand);
        } else {
            throw new UserProfilePromotionException();
        }
    }

    @Override
    public UserProfile findProfileById(String id) {
        return userProfileQueryPort.getProfileById(id);
    }

    @Override
    public List<UserProfile> findUsersByCriteria(SearchUserProfileQuery searchUserProfileQuery) {
        return null;
    }

    @Override
    public List<UserProfile> getActiveUsers() {
        return userProfileQueryPort.getAllActiveUsers();
    }

    // TODO less hardcoded solution - test purpose only
    private UserProfile persistPromotedProfile(UserProfile userProfile, ProfilePromotionCommand profilePromotionCommand){
        if(profilePromotionCommand.promotionType() == ProfileType.SELLER){
            return userRegistrationPort.registerUser(userMapper.mapToSeller(userProfile,
                    LocalDateTime.now(),
                    profilePromotionCommand.shop().shopInfo(),
                    profilePromotionCommand.shop().address()));
        }
        return new UserProfile(null, null, null);
    }

}
