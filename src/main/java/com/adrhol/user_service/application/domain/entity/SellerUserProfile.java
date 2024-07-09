package com.adrhol.user_service.application.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
@Getter
@Setter
public class SellerUserProfile extends UserProfile{

    @Field
    private LocalDateTime policyTimeToLive;
    @Field
    private ShopInfo shopInfo;
    @Field
    private Address address;

    protected SellerUserProfile() {
        super();
    }
    protected SellerUserProfile(final String id,
                                final String accountId,
                                final ProfileOwner profileOwner,
                                final LocalDateTime policyTimeToLive,
                                final ShopInfo shopInfo,
                                final Address address,
                                final ProfileType profileType) {
        super(id, accountId, profileOwner, profileType);
        this.policyTimeToLive = policyTimeToLive;
        this.shopInfo = shopInfo;
        this.address = address;
    }
    public SellerUserProfile(final String id,
                             final String accountId,
                             final ProfileOwner profileOwner,
                             final LocalDateTime policyTimeToLive,
                             final ShopInfo shopInfo,
                             final Address address) {
        super(id, accountId, profileOwner, ProfileType.SELLER);
        this.policyTimeToLive = policyTimeToLive;
        this.shopInfo = shopInfo;
        this.address = address;
    }
}
