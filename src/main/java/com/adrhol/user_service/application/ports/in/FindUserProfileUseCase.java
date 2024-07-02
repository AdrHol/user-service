package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.DomainUser;

import java.util.List;
import java.util.Optional;

public interface FindUserProfileUseCase {

    DomainUser findProfileById(String id);
    List<DomainUser> findUsersByCriteria(SearchUserProfileQuery searchUserProfileQuery);
}
