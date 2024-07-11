package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.ProfileType;

import java.time.LocalDateTime;

public record ProfilePromotionCommand(String profileId, ProfileType promotionType, LocalDateTime expirationDate) {

}
