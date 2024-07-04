package com.adrhol.user_service.application.ports.in;

public record CreateUserCommand(String accountId, String firstName, String lastName) {

}
