package com.adrhol.user_service.application.ports.in;

import com.adrhol.user_service.application.domain.entity.ProfileOwner;

public record CreateUserCommand(String accountId, String firstName, String lastName) {

}
