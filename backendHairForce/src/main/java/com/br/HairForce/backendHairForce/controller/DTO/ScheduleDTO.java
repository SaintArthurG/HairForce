package com.br.HairForce.backendHairForce.controller.DTO;

import com.br.HairForce.backendHairForce.entity.Service;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleDTO {
    private Long barberId;
    private LocalDateTime time;
    private List<Service> services;

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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
