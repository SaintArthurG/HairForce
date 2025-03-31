package com.br.HairForce.backendHairForce.controller.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleDTO {
    private Long barberId;
    private LocalDateTime time;
    private List<String> services;

    public Long getBarberId() {
        return barberId;
    }

    public void setBarberId(Long barberId) {
        this.barberId = barberId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
