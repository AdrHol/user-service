package com.adrhol.user_service.adapters.out.persistence.mapper.strategies;

import com.adrhol.user_service.application.domain.entity.ProfileType;
import com.adrhol.user_service.application.domain.entity.UserProfile;

public interface UserMappingStrategy<T> {
    public ProfileType getProfileType();
    public T map(UserProfile userProfile);

}
