package com.br.HairForce.backendHairForce.service;

import com.br.HairForce.backendHairForce.controller.DTO.ScheduleDTO;
import com.br.HairForce.backendHairForce.entity.Barber;
import com.br.HairForce.backendHairForce.entity.Schedule;
import com.br.HairForce.backendHairForce.repository.BarberRepository;
import com.br.HairForce.backendHairForce.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private BarberService barberService;

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(BarberService barberService, ScheduleRepository scheduleRepository) {
        this.barberService = barberService;
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getScheduleByBarber(Long barberId){
        return scheduleRepository.findByBarberId(barberId);
    }

    public Schedule saveSchedule(ScheduleDTO scheduleDTO){
        Barber barber = barberService.getBarberById(scheduleDTO.getBarberId());
        Schedule schedule = new Schedule();
        schedule.setBarber(barber);
        schedule.setTime(scheduleDTO.getTime());
        schedule.setServices(scheduleDTO.getServices());
    }
}
