package com.adrhol.user_service.application.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DomainUser {
    private String id;
    private String firstName;
    private String lastName;
}
