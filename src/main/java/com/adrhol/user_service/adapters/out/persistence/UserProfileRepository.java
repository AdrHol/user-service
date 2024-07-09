package com.adrhol.user_service.adapters.out.persistence;

import com.adrhol.user_service.application.domain.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

}
