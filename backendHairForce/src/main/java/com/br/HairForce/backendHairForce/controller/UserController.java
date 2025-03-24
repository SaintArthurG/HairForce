package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.entity.User;
import com.br.HairForce.backendHairForce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cadastro")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody CreateUserDto createUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(null);  // Ou retorne detalhes do erro
        }

        UUID userId = userService.createUser(createUserDto);
        return ResponseEntity.ok(userId);
    }


    @GetMapping("/{userId}")  // Correct the path variable to match the method parameter
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        // You need to implement the service call and return a user
        User user = userService.getUserById(UUID.fromString(userId));
        if (user == null) {
            return ResponseEntity.notFound().build();  // Return 404 if user not found
        }
        return ResponseEntity.ok(user);
    }

}
