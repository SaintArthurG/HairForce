package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.entity.Barber;
import com.br.HairForce.backendHairForce.service.BarberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbers")
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

    @GetMapping
    public ResponseEntity<List<Barber>> getAllBarber(){
        return ResponseEntity.ok(barberService.getAllBarbers());
    }
}
