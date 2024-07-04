package com.adrhol.user_service.application.ports.in;

public record UpdateUserCommand(String profileId, String firstName, String lastName) {
}
