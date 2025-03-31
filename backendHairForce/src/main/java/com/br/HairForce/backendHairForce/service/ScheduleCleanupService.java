package com.br.HairForce.backendHairForce.service;

import com.br.HairForce.backendHairForce.entity.Schedule;
import com.br.HairForce.backendHairForce.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleCleanupService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Scheduled(cron = "0 0 0 * * ?") // Roda à meia-noite
    public void setExpiredSchedules() {
        LocalDateTime ontem = LocalDateTime.now().minusDays(1);
        List<Schedule> expirados = scheduleRepository.findByTimeBeforeAndExpiredFalse(ontem);

        for (Schedule schedule : expirados) {
            schedule.setExpired(true);
        }

        scheduleRepository.saveAll(expirados);
        System.out.println("Agendamentos do dia anterior marcados como expirados.");
    }
}