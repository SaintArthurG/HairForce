package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.controller.DTO.ScheduleDTO;
import com.br.HairForce.backendHairForce.entity.Schedule;
import com.br.HairForce.backendHairForce.repository.ScheduleRepository;
import com.br.HairForce.backendHairForce.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleDTO scheduleDTO){
        try {
            scheduleService.saveSchedule(scheduleDTO);
            return ResponseEntity.ok("Agendamento criado com sucesso");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<Schedule>> getActiveSchedules(){
        List<Schedule> activeSchedules = scheduleService.getActiveSchedules();
        return ResponseEntity.ok(activeSchedules);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Schedule>> getAllSchedules(){
        List<Schedule> allSchedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(allSchedules);
    }

}
