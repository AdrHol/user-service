package com.adrhol.user_service.adapters.out.persistance;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileCreationAdapterImpl implements UserProfileCreationAdapter {

    private final UserProfileMongoRepository userProfileRepository;

    @Autowired
    public UserProfileCreationAdapterImpl(UserProfileMongoRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfileMongoEntity registerUser(CreateUserCommand command) {
        UserProfileMongoEntity user = new UserProfileMongoEntity(null, command.firstName(), command.lastName());
        return userProfileRepository.save(user);
    }
}
