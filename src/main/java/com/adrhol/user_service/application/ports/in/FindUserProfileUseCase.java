package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.UserProfile;

import java.util.List;

public interface FindUserProfileUseCase {

    UserProfile findProfileById(String id);
    List<UserProfile> findUsersByCriteria(SearchUserProfileQuery searchUserProfileQuery);
}
