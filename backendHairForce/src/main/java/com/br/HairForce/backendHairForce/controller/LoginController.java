package com.br.HairForce.backendHairForce.controller;


import com.br.HairForce.backendHairForce.domain.login.DadosLogin;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity login (@RequestBody @Valid DadosLogin dadosLogin){

        return ResponseEntity.ok().build();

    }
}
