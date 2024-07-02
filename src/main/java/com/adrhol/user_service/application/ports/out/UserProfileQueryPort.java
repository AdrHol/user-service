package com.adrhol.user_service.application.ports.out;

import com.adrhol.user_service.application.domain.entity.DomainUser;

import java.util.List;

public interface UserProfileQueryPort {

    public DomainUser getUserById(String id);
    public List<DomainUser> getAllActiveUsers();
}
