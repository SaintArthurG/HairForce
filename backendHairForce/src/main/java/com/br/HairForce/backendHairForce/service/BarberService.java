package com.br.HairForce.backendHairForce.service;

import com.br.HairForce.backendHairForce.controller.DTO.CreateBarberDTO;
import com.br.HairForce.backendHairForce.entity.Barber;
import com.br.HairForce.backendHairForce.entity.Schedule;
import com.br.HairForce.backendHairForce.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BarberService {
    private final BarberRepository barberRepository;

    private final ScheduleService scheduleService;

    @Autowired
    public BarberService(BarberRepository barberRepository, ScheduleService scheduleService) {
        this.barberRepository = barberRepository;
        this.scheduleService = scheduleService;
    }


    public Barber createBarber(Barber barber){
        return barberRepository.save(barber);
    }

    public Barber getBarberById(Long id){
        return barberRepository.findById(id).orElse(null);
    }

    public Schedule scheduleServiceForBarber(Long barberId, String serviceType, LocalDateTime serviceTime) {
        return scheduleService.createSchedule(barberId, serviceType, serviceTime);
    }
}
