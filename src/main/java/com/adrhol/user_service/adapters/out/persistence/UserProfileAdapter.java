package com.adrhol.user_service.adapters.out.persistence;

import com.adrhol.user_service.adapters.out.persistence.mapper.UserMapper;
import com.adrhol.user_service.application.domain.entity.ProfileOwner;
import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.domain.exceptions.UserProfileNotFoundException;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import com.adrhol.user_service.application.ports.in.UpdateUserCommand;
import com.adrhol.user_service.application.ports.out.UserProfileQueryPort;
import com.adrhol.user_service.application.ports.out.UserRegistrationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserProfileAdapter implements UserRegistrationPort, UserProfileQueryPort {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileAdapter(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile registerUser(UserProfile entity) {
        return userProfileRepository.save(entity);
    }

    @Override
    public UserProfile updateUser(UserProfile updatedUser) {
        return userProfileRepository.save(updatedUser);
    }

    @Override
    public UserProfile getUserById(String id) {
        return userProfileRepository.findById(id).orElseThrow(UserProfileNotFoundException::new);
    }

    @Override
    public Optional<UserProfile> getUserByAccountId(String accountId) {
        return userProfileRepository.findProfileByUserAccountId(accountId);
    }

    @Override
    public List<UserProfile> getAllActiveUsers() {
        return userProfileRepository.findAll();
    }
}
