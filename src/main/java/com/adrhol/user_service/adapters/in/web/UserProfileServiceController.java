package com.adrhol.user_service.adapters.in.web;

import com.adrhol.user_service.application.domain.entity.UserProfile;
import com.adrhol.user_service.application.ports.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserProfileServiceController {

    private final RegisterUserProfileUseCase registerUserProfileUseCase;
    private final DeactivateUserProfileUseCase deactivateUserUseCase;
    private final RetrieveActiveProfilesUseCase retrieveActiveProfilesUseCase;
    private final FindUserProfileUseCase findUserProfileUseCase;
    private final ProfileDetailsEditionUseCase profileDetailsEditionUseCase;

    @GetMapping("/all")
    public ResponseEntity<List<UserProfile>> getAllActiveUsers(){
        return ResponseEntity.ok(retrieveActiveProfilesUseCase.getActiveUsers());
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable String id){
        return ResponseEntity.ok(findUserProfileUseCase.findProfileById(id));
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserProfile> createUser(@Validated @RequestBody CreateUserCommand createUserCommand){
        UserProfile createdUser = registerUserProfileUseCase.registerUser(createUserCommand);
        return ResponseEntity.ok(createdUser);
    }
    @PostMapping("/search")
    public ResponseEntity<List<UserProfile>> findProfileByCriteria(SearchUserProfileQuery searchUserProfileQuery){
        return ResponseEntity.ok(findUserProfileUseCase.findUsersByCriteria(searchUserProfileQuery));
    }
    @PutMapping("/profile/{id}")
    public ResponseEntity<UserProfile> updateUser(@Validated UpdateUserCommand updateUserCommand){
        return ResponseEntity.ok(profileDetailsEditionUseCase.updateProfile(updateUserCommand));
    }
    @DeleteMapping("/profile/{id}/permission")
    public ResponseEntity<UserProfile> removePermission(@PathVariable String id){
        return ResponseEntity.ok(deactivateUserUseCase.removePremium(id));
    }
    @DeleteMapping("/profile/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        deactivateUserUseCase.removeProfile(id);
        return ResponseEntity.ok("User deactivated");
    }
    @PostMapping("/promote")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserProfile> promoteUserProfile(@RequestBody ProfilePromotionCommand profilePromotionCommand){
        return ResponseEntity.ok(profileDetailsEditionUseCase.promoteProfile(profilePromotionCommand));
    }

}
