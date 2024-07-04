package com.adrhol.user_service.adapters.out.persistence;

import com.adrhol.user_service.application.domain.entity.UserProfileMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileMongoRepository extends MongoRepository<UserProfileMongoEntity, String> {

    Optional<UserProfileMongoEntity> findProfileByUserAccountId(String id);
}
