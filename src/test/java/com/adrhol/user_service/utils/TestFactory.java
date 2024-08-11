package com.adrhol.user_service.utils;

import com.adrhol.user_service.application.domain.entity.ProfileOwner;
import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.ports.in.CreateUserCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestFactory {

    public static List<UserProfile> generateUserList(int profilesCount){
        List<UserProfile> users = new ArrayList<>();
        for (int i = 0; i < profilesCount ; i++){
            users.add(generateRegularUserProfile());
        }
        return users;
    }
    public static UserProfile generateRegularUserProfile(){
        return new UserProfile(generateRandomString(3), generateRandomString(5), generateProfileOwner());
    }
    public static UserProfile createProfileFromCommand(CreateUserCommand createUserCommand){
        return new UserProfile(generateRandomString(5),
                createUserCommand.accountId(),
                new ProfileOwner(createUserCommand.firstName(), createUserCommand.lastName()));
    }
    public static ProfileOwner generateProfileOwner(){
        return new ProfileOwner(generateRandomString(6), generateRandomString(4));
    }
    public static String generateRandomString(int length){
        int startingCode = 48;
        int endingCode = 123;
        Random random = new Random();

        return random.ints(startingCode, endingCode)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
}
