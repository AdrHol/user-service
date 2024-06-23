package com.adrhol.user_service.adapters.in.web;

import com.adrhol.user_service.application.domain.entity.DomainUser;
import com.adrhol.user_service.application.ports.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserProfileServiceController {

    private final RegisterUserProfileUseCase registerUserProfileUseCase;
    private final DeactivateUserProfileUseCase deactivateUserUseCase;
    private final RetrieveActiveProfilesUseCase retrieveActiveProfilesUseCase;
    private final FindUserProfileUseCase findUserProfileUseCase;
    private final UpdateUserProfileUseCase updateUserProfileUseCase;

    @GetMapping("/all")
    public ResponseEntity<List<DomainUser>> getAllActiveUsers(){
        return ResponseEntity.ok(retrieveActiveProfilesUseCase.getActiveUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DomainUser> getUserProfile(@PathVariable String id){
        Optional<DomainUser> userProfile = findUserProfileUseCase.findProfileById(id);
        return ResponseEntity.ok(userProfile.orElse(new DomainUser()));
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DomainUser> createUser(@Validated CreateUserCommand createUserCommand){
        DomainUser createdUser = registerUserProfileUseCase.registerUser(createUserCommand);
        return ResponseEntity.ok(createdUser);
    }
    @PostMapping("/search")
    public ResponseEntity<List<DomainUser>> findProfileByCriteria(SearchUserProfileQuery searchUserProfileQuery){
        return ResponseEntity.ok(findUserProfileUseCase.findUsersByCriteria(searchUserProfileQuery));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DomainUser> updateUser(@PathVariable String id, @Validated CreateUserCommand createUserCommand){
        return ResponseEntity.ok(updateUserProfileUseCase.updateProfile(createUserCommand));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        deactivateUserUseCase.deactivateProfile(id);
        return ResponseEntity.ok("User deactivated");
    }

}
