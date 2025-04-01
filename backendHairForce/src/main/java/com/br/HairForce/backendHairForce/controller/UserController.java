package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.controller.DTO.CreateUserDTO;
import com.br.HairForce.backendHairForce.entity.User;
import com.br.HairForce.backendHairForce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO createUserDto) {
        String userName = userService.createUser(createUserDto);
        return ResponseEntity.ok(userName);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showUsers (){
        return ResponseEntity.ok(userService.showUsers());
    }

}
