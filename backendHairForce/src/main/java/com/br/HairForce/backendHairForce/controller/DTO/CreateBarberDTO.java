package com.br.HairForce.backendHairForce.controller.DTO;

import java.util.List;

public class CreateBarberDTO {
    private String name;
    private List<String> services;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
