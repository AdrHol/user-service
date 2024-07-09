package com.adrhol.user_service.adapters.out.persistence.mapper.strategies;

import com.adrhol.user_service.adapters.out.persistence.mapper.exceptions.MappingException;
import com.adrhol.user_service.application.domain.entity.ProfileType;
import com.adrhol.user_service.application.domain.entity.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class RegularUserMapper implements UserMappingStrategy<UserProfile>{

    @Override
    public ProfileType getProfileType() {
        return ProfileType.BASIC;
    }

    @Override
    public UserProfile map(final UserProfile userProfile) {
        if(userProfile == null){
            throw new MappingException();
        }
        return userProfile;
    }

}
