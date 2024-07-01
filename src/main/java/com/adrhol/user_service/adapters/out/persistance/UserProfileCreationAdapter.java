package com.adrhol.user_service.adapters.out.persistance;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;

public interface UserProfileCreationAdapter {

    UserProfileMongoEntity registerUser(CreateUserCommand user);
}
