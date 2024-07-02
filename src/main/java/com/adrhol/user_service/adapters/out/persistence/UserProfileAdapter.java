package com.adrhol.user_service.adapters.out.persistence;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.domain.exceptions.UserProfileNotExistsException;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import com.adrhol.user_service.application.ports.out.UserProfileQueryPort;
import com.adrhol.user_service.application.ports.out.UserRegistrationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public DomainUser registerUser(CreateUserCommand command) {
        UserProfileMongoEntity userRequest = userMapper.creationCommandToEntity(command);
        return userMapper.userProfileToDomainEntity(userProfileRepository.save(userRequest));
    }

    @Override
    public DomainUser getUserById(String id) {
        UserProfileMongoEntity user = userProfileRepository.findById(id).orElseThrow(UserProfileNotExistsException::new);
        return userMapper.userProfileToDomainEntity(user);
    }

    @Override
    public List<DomainUser> getAllActiveUsers() {
        return userMapper.userProfileToDomainEntity(userProfileRepository.findAll());
    }
}
