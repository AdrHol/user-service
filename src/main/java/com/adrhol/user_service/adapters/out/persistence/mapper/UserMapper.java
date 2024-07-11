package com.adrhol.user_service.adapters.out.persistence.mapper;

import com.adrhol.user_service.adapters.out.persistence.mapper.strategies.RegularUserMapper;
import com.adrhol.user_service.adapters.out.persistence.mapper.strategies.UserMappingStrategy;
import com.adrhol.user_service.application.domain.entity.*;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserMapper {

    private Map<ProfileType, UserMappingStrategy<?>> mappingStrategies;

    public UserMapper() {
        this.mappingStrategies = new HashMap<>();
        this.mappingStrategies.put(ProfileType.BASIC, new RegularUserMapper());
    }

    public UserProfile creationCommandToEntity(CreateUserCommand createUserCommand){
        return new UserProfile(null, createUserCommand.accountId(), new ProfileOwner(createUserCommand.firstName(),
                                                                                     createUserCommand.lastName()));
    }

    public SellerUserProfile mapToSeller(UserProfile userProfile, LocalDateTime expirationDate, ShopInfo shopInfo, Address address){
        return new SellerUserProfile(userProfile.getId(),
                userProfile.getAccountId(),
                userProfile.getProfileOwner(),
                expirationDate,
                shopInfo,
                address);
    }

}
