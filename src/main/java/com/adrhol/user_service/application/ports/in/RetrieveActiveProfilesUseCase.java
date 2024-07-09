package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.UserProfile;

import java.util.List;

public interface RetrieveActiveProfilesUseCase {

    List<UserProfile> getActiveUsers();
}
