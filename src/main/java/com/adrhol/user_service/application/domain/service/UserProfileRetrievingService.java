package com.adrhol.user_service.application.domain.service;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.ports.in.*;
import com.adrhol.user_service.application.ports.out.UserProfileQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileRetrievingService implements RetrieveActiveProfilesUseCase, FindUserProfileUseCase, SearchUserProfileQuery {

    private final UserProfileQueryPort userProfileQueryPort;

    @Override
    public DomainUser findProfileById(String id) {
        return userProfileQueryPort.getUserById(id);
    }

    @Override
    public List<DomainUser> findUsersByCriteria(SearchUserProfileQuery searchUserProfileQuery) {
        return null;
    }

    @Override
    public List<DomainUser> getActiveUsers() {
        return userProfileQueryPort.getAllActiveUsers();
    }

}
