package com.br.HairForce.backendHairForce.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // Aqui você pode adicionar sua lógica de autenticação, mas vamos apenas responder com um sucesso
        System.out.println("Login recebido: " + user.getUsername() + ", " + user.getPassword());
        return "Login bem-sucedido!";
    }

    public static class User {
        private String username;
        private String password;

        // Getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
