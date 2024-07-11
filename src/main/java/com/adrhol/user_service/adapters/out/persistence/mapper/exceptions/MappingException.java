package com.adrhol.user_service.adapters.out.persistence.mapper.exceptions;

public class MappingException extends RuntimeException{

    public MappingException() {
        super("Error has occurred during entity mapping");
    }

}
