package com.adrhol.user_service.application.domain.exceptions;

public class UserProfilePromotionException extends RuntimeException{

    public UserProfilePromotionException(){
        super("User already promoted");
    }
}
