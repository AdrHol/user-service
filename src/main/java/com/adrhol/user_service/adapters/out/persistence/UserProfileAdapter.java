package com.adrhol.user_service.adapters.out.persistence;

import com.adrhol.user_service.adapters.out.persistence.mapper.UserMapper;
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

    private final UserProfileMongoRepository userProfileRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserProfileAdapter(UserProfileMongoRepository userProfileRepository, UserMapper userMapper) {
        this.userProfileRepository = userProfileRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserProfile registerUser(CreateUserCommand command) {
        UserProfileMongoEntity userRequest = userMapper.creationCommandToEntity(command);
        return userMapper.userProfileToDomainEntity(userProfileRepository.save(userRequest));
    }

    @Override
    public UserProfile updateUser(UpdateUserCommand updateUserCommand) {
        UserProfileMongoEntity user = userProfileRepository.findById(updateUserCommand.profileId())
                                                           .orElseThrow(UserProfileNotFoundException::new);
        user.setFirstName(updateUserCommand.firstName());
        user.setLastName(updateUserCommand.lastName());

        return userMapper.userProfileToDomainEntity(userProfileRepository.save(user));
    }

    @Override
    public UserProfile getUserById(String id) {
        UserProfileMongoEntity user = userProfileRepository.findById(id).orElseThrow(UserProfileNotFoundException::new);
        return userMapper.userProfileToDomainEntity(user);
    }

    @Override
    public Optional<UserProfileMongoEntity> getUserByAccountId(String accountId) {
        return userProfileRepository.findProfileByUserAccountId(accountId);
    }

    @Override
    public List<UserProfile> getAllActiveUsers() {
        return userMapper.userProfileToDomainEntity(userProfileRepository.findAll());
    }
}
