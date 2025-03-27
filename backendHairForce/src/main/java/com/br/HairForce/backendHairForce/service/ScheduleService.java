package com.br.HairForce.backendHairForce.service;

import com.br.HairForce.backendHairForce.entity.Schedule;
import com.br.HairForce.backendHairForce.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule saveSchedule(Schedule schedule){
        if(scheduleRepository.existByBarberIdAndHour(schedule.getBarber().getId(), schedule.getTime())){
            throw new RuntimeException("Este horario ja esta reservado para este barbeiro");
        }
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> scheduleList(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> listPerBarber(Long barberId){
        return scheduleRepository.findByBarberId(barberId);
    }




}
