package com.br.HairForce.backendHairForce.repository;

import com.br.HairForce.backendHairForce.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByBarberId(Long barberId);
    boolean existByBarberIdAndHour(Long barberId, LocalDateTime time);
}
