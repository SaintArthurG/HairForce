package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.entity.Barber;
import com.br.HairForce.backendHairForce.service.BarberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barber")
public class BarberController {
    private final BarberService barberService;

    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Barber> createBarber(@RequestBody Barber barber) {
        Barber barberName = barberService.createBarber(barber);
        return ResponseEntity.ok(barberName);
    }
}
