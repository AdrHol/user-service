package com.adrhol.user_service.adapters.out.persistence.mapper;

import com.adrhol.user_service.adapters.out.persistence.mapper.strategies.RegularUserMapper;
import com.adrhol.user_service.adapters.out.persistence.mapper.strategies.UserMappingStrategy;
import com.adrhol.user_service.application.domain.entity.ProfileOwner;
import com.adrhol.user_service.application.domain.entity.ProfileType;
import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserMapper {

    private Map<ProfileType, UserMappingStrategy<?>> mappingStrategies;

    public UserMapper() {
        this.mappingStrategies = new HashMap<>();
        this.mappingStrategies.put(ProfileType.BASIC, new RegularUserMapper());
    }

    public UserProfile creationCommandToEntity(CreateUserCommand createUserCommand){
        return new UserProfile(null, createUserCommand.accountId(), new ProfileOwner(createUserCommand.firstName(),
                                                                                     createUserCommand.lastName()));
    }

}
