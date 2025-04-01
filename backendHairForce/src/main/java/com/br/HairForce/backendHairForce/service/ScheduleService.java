package com.br.HairForce.backendHairForce.service;

import com.br.HairForce.backendHairForce.controller.DTO.ScheduleDTO;
import com.br.HairForce.backendHairForce.entity.Barber;
import com.br.HairForce.backendHairForce.entity.Schedule;
import com.br.HairForce.backendHairForce.repository.ScheduleRepository;
import com.br.HairForce.backendHairForce.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ScheduleService {

    private final BarberService barberService;

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(BarberService barberService, ScheduleRepository scheduleRepository) {
        this.barberService = barberService;
        this.scheduleRepository = scheduleRepository;
    }

    public boolean isTimeAvailable(Long barberId, LocalDateTime time) {
        System.out.println("Verificando disponibilidade: Barbeiro ID = " + barberId + ", Hora = " + time);
        return !scheduleRepository.existsByBarberIdAndTime(barberId, time);
    }

    public List<Schedule> getScheduleByBarber(Long barberId){
        return scheduleRepository.findByBarberId(barberId);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getActiveSchedules(){
        return scheduleRepository.findByExpiredFalse();
    }


    //adicionando novo horario e vinculando ao barber
    public Schedule saveSchedule(ScheduleDTO scheduleDTO){
        Barber barber = barberService.getBarberById(scheduleDTO.getBarberId());
        if(barber == null){
            throw new IllegalArgumentException("Barbeiro nao encontrado");
        }

        if(!isTimeAvailable(scheduleDTO.getBarberId(), scheduleDTO.getTime())){
            throw new RuntimeException("Horário indisponível.");
        }

        if (scheduleDTO.getServices().isEmpty()){
            throw new IllegalArgumentException("Serviços não encontrados");
        }

        Schedule schedule = new Schedule();
        schedule.setBarber(barber);
        schedule.setTime(scheduleDTO.getTime());

        schedule.setServices(scheduleDTO.getServices());

        return scheduleRepository.save(schedule);
    }


}
