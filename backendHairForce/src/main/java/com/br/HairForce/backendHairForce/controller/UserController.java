package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.entity.User;
import com.br.HairForce.backendHairForce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Long> createUser(@RequestBody CreateUserDto createUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(null);  // Ou retorne detalhes do erro
        }
        Long userId = userService.createUser(createUserDto);
        return ResponseEntity.ok(userId);
    }


    @GetMapping("/")
    public String msg (){
        return "ok";
    }

}
