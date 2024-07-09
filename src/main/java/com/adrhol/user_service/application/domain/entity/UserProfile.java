package com.adrhol.user_service.application.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user_profiles")
@Getter
@Setter
public class UserProfile {
    @Id
    private String id;
    @Field
    private String accountId;
    @Field
    private ProfileOwner profileOwner;
    @Field
    private ProfileType profileType;

    protected UserProfile() {}
    protected UserProfile(final String id,
                       final String accountId,
                       final ProfileOwner profileOwner,
                       final ProfileType profileType) {
        this.id = id;
        this.accountId = accountId;
        this.profileOwner = profileOwner;
        this.profileType = profileType;
    }

    public UserProfile(final String id, final String accountId, final ProfileOwner profileOwner) {
        this.id = id;
        this.accountId = accountId;
        this.profileOwner = profileOwner;
        this.profileType = ProfileType.BASIC;
    }

}
