package com.br.HairForce.backendHairForce.repository;

import com.br.HairForce.backendHairForce.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByBarberId(Long barberId);

    boolean existsByBarberIdAndTime(Long barberId, LocalDateTime time);

    List<Schedule> findByTimeBeforeAndExpiredFalse(LocalDateTime time);

    List<Schedule> findByExpiredFalse();
}
