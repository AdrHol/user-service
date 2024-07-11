package com.adrhol.user_service.common.validation;

import com.adrhol.user_service.application.domain.entity.ProfileType;
import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.domain.exceptions.ProfileAlreadyInUseException;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import com.adrhol.user_service.application.ports.out.UserProfileQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileUserProfileValidatorImpl implements UserProfileValidator {

    private final UserProfileQueryPort userProfileQueryPort;

    @Autowired
    public UserProfileUserProfileValidatorImpl(final UserProfileQueryPort userProfileQueryPort) {
        this.userProfileQueryPort = userProfileQueryPort;
    }

    @Override
    public boolean throwIfAccountHasAssignedProfile(final String userId) {
        if(userProfileQueryPort.getUserByAccountId(userId).isPresent()){
            throw new ProfileAlreadyInUseException();
        }
        return true;
    }

    @Override
    public boolean canBePromoted(UserProfile userProfile, ProfileType promotedTo) {
        if(userProfile.getProfileType() == promotedTo){
            return false;
        } else return true;
    }

}
