package com.br.HairForce.backendHairForce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class HomeController {


    @GetMapping    public String msg (){
        return "oi";
    }
}
