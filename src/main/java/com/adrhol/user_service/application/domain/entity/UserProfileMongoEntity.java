package com.adrhol.user_service.application.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user_profiles")
@Getter
@Setter
public class UserProfileMongoEntity {

    @Id
    private String id;
    @Field("account_id")
    private String userAccountId;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;

    public UserProfileMongoEntity(String id, String userAccountId, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAccountId = userAccountId;
    }

}
