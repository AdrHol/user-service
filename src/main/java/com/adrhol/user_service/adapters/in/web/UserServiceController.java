package com.adrhol.user_service.adapters.in.web;

import com.adrhol.user_service.application.domain.entity.User;
import com.adrhol.user_service.application.ports.in.CRUDUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserServiceController {

    private final CRUDUserUseCase crudUserUseCase;
    @GetMapping("/{id}")
    public ResponseEntity<String> getUserProfile(@PathVariable String id){
        return ResponseEntity.ok("Hello");
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@Validated CreateUserCommand createUserCommand){
        return ResponseEntity.ok(new User());
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @Validated CreateUserCommand createUserCommand){
        return ResponseEntity.ok(new User());
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        return ResponseEntity.ok("User removed");
    }

}
