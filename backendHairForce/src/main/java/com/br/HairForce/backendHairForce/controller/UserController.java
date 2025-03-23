package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody String body){

        return null;
    }

    @GetMapping("/{userID}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        return null;
    }


}
