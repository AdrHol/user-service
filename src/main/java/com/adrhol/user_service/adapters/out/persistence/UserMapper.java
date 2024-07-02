package com.adrhol.user_service.adapters.out.persistence;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public DomainUser userProfileToDomainEntity(UserProfileMongoEntity userProfileMongoEntity){
        return new DomainUser(
                userProfileMongoEntity.getId(),
                userProfileMongoEntity.getFirstName(),
                userProfileMongoEntity.getLastName());
    }

    public List<DomainUser> userProfileToDomainEntity(List<UserProfileMongoEntity> userProfiles){
        return userProfiles.stream().map(this::userProfileToDomainEntity).toList();
    }
    public UserProfileMongoEntity creationCommandToEntity(CreateUserCommand createUserCommand){
        return new UserProfileMongoEntity(null, createUserCommand.firstName(), createUserCommand.lastName());
    }
}
