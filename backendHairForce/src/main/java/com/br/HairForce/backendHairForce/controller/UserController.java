package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.entity.User;
import com.br.HairForce.backendHairForce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDto createUserDto) {
        String userName = userService.createUser(createUserDto);
        return ResponseEntity.ok(userName);
    }

    @GetMapping("/")
    public String msg (){
        return "ok";
    }

}
