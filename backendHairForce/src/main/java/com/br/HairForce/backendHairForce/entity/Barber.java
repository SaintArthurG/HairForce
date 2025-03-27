package com.br.HairForce.backendHairForce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    public Barber(){}

    public Barber(Long id, String name, List<Schedule> schedules) {
        this.id = id;
        this.name = name;
        this.schedules = schedules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

}
