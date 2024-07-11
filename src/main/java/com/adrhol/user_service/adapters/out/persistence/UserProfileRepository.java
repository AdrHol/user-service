package com.adrhol.user_service.adapters.out.persistence;

import com.adrhol.user_service.application.domain.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    Optional<UserProfile> findProfileByUserAccountId(String id);
    <U extends UserProfile> U save(U entity);
}
