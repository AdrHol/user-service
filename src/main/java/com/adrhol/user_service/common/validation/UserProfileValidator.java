package com.adrhol.user_service.common.validation;

public interface UserProfileValidator {
   boolean throwIfAccountHasAssignedProfile(String userId);
}
