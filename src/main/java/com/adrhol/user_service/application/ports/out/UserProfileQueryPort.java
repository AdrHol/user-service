package com.adrhol.user_service.application.ports.out;

import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;

import java.util.List;
import java.util.Optional;

public interface UserProfileQueryPort {

    UserProfile getUserById(String id);
    Optional<UserProfileMongoEntity> getUserByAccountId(String accountId);
    List<UserProfile> getAllActiveUsers();
}
