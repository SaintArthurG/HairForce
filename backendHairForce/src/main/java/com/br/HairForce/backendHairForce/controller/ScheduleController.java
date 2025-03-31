package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.controller.DTO.ScheduleDTO;
import com.br.HairForce.backendHairForce.entity.Schedule;
import com.br.HairForce.backendHairForce.repository.ScheduleRepository;
import com.br.HairForce.backendHairForce.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @PostMapping
    public Schedule createSchedule(@RequestBody ScheduleDTO scheduleDTO){
        return scheduleService.saveSchedule(scheduleDTO);
    }

    @GetMapping("/barber/{barberId}/")
    public List<Schedule> getSchedulesByBarber(@PathVariable Long barberId) {
        return scheduleRepository.findByBarberId(barberId);
    }
}
