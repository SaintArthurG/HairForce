package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.entity.Schedule;
import com.br.HairForce.backendHairForce.service.ScheduleService;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Endpoint para obter os agendamentos de um cabeleireiro
    @GetMapping("/barber/{barberId}/schedules")
    public List<Schedule> getSchedulesByBarber(@PathVariable Long barberId) {
        return scheduleService.getScheduleByBarber(barberId);
    }
}
