package com.adrhol.user_service.adapters.out.persistence;

import com.adrhol.user_service.application.domain.entity.SellerUserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerUserRepository extends MongoRepository<SellerUserProfile, String> {

}
