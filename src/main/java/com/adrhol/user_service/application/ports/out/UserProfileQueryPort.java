package com.adrhol.user_service.application.ports.out;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.ports.in.UpdateUserCommand;

import java.util.List;
import java.util.Optional;

public interface UserProfileQueryPort {

    DomainUser getUserById(String id);
    Optional<UserProfileMongoEntity> getUserByAccountId(String accountId);
    List<DomainUser> getAllActiveUsers();
}
