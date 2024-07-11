package com.adrhol.user_service.common.validation;

import com.adrhol.user_service.application.domain.entity.ProfileType;
import com.adrhol.user_service.application.domain.entity.UserProfile;

public interface UserProfileValidator {
   boolean throwIfAccountHasAssignedProfile(String userId);
   boolean canBePromoted(UserProfile userProfile, ProfileType promotedTo);
}
