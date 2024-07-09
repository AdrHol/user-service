package com.adrhol.user_service.adapters.out.persistence;

import com.adrhol.user_service.application.domain.entity.PremiumUserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PremiumUserRepository extends MongoRepository<PremiumUserProfile, String> {

}
