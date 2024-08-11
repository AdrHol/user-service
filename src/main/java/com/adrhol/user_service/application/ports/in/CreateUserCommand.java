package com.adrhol.user_service.application.ports.in;


import jakarta.validation.constraints.NotNull;

public record CreateUserCommand(@NotNull String accountId,
                                @NotNull String firstName,
                                @NotNull String lastName) {
}
