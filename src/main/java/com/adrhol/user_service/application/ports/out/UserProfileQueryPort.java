package com.adrhol.user_service.application.ports.out;

import com.adrhol.user_service.application.domain.entity.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileQueryPort {

    UserProfile getProfileById(String id);
    Optional<UserProfile> getUserByAccountId(String accountId);
    List<UserProfile> getAllActiveUsers();
}
