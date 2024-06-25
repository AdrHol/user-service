package com.adrhol.user_service.application.domain.service;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.ports.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileRetrievingService implements RetrieveActiveProfilesUseCase, FindUserProfileUseCase, SearchUserProfileQuery {


    @Override
    public Optional<DomainUser> findProfileById(String id) {
        return Optional.empty();
    }

    @Override
    public List<DomainUser> findUsersByCriteria(SearchUserProfileQuery searchUserProfileQuery) {
        return null;
    }

    @Override
    public List<DomainUser> getActiveUsers() {
        return null;
    }

}
