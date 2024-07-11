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
    private final UserMapper userMapper;

    @Autowired
    public UserProfileAdapter(UserProfileRepository userProfileRepository, UserMapper userMapper) {
        this.userProfileRepository = userProfileRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserProfile registerUser(CreateUserCommand command) {
        UserProfile userRequest = userMapper.creationCommandToEntity(command);
        return userProfileRepository.save(userRequest);
    }

    @Override
    public UserProfile updateUser(UpdateUserCommand updateUserCommand) {
        UserProfile user = userProfileRepository.findById(updateUserCommand.profileId())
                                                           .orElseThrow(UserProfileNotFoundException::new);

        user.setProfileOwner(new ProfileOwner(updateUserCommand.firstName(), updateUserCommand.lastName()));

        return userProfileRepository.save(user);
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
