package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.Address;
import com.adrhol.user_service.application.domain.entity.ShopInfo;

public record ShopCreationCommand(ShopInfo shopInfo, Address address) {
}
