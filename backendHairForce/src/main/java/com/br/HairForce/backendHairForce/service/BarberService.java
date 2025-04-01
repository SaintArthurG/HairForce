package com.br.HairForce.backendHairForce.service;

import com.br.HairForce.backendHairForce.controller.DTO.CreateBarberDTO;
import com.br.HairForce.backendHairForce.entity.Barber;
import com.br.HairForce.backendHairForce.entity.Schedule;
import com.br.HairForce.backendHairForce.entity.User;
import com.br.HairForce.backendHairForce.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BarberService {
    private final BarberRepository barberRepository;


    @Autowired
    public BarberService(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }


    public Barber createBarber(Barber barber){
        return barberRepository.save(barber);
    }

    public Barber getBarberById(Long id){
        return barberRepository.findById(id).orElse(null);
    }

    public List<Barber> getAllBarbers(){
        return barberRepository.findAll();
    }

}
