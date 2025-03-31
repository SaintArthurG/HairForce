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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime scheduleTime = LocalDateTime.parse(scheduleDTO.getTime(), formatter);

        if(!isTimeAvailable(scheduleDTO.getBarberId(), scheduleTime)){
            throw new RuntimeException("Horário indisponível.");
        }

        if (scheduleDTO.getServices().isEmpty()){
            throw new IllegalArgumentException("Serviços não encontrados");
        }

        Schedule schedule = new Schedule();
        schedule.setBarber(barber);
        schedule.setTime(scheduleTime);
        schedule.setServices(scheduleDTO.getServices());

        return scheduleRepository.save(schedule);
    }


}
