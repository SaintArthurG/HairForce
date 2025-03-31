package com.br.HairForce.backendHairForce.repository;

import com.br.HairForce.backendHairForce.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BarberRepository extends JpaRepository<Barber, Long>{
}
