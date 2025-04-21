package com.br.HairForce.backendHairForce.domain.barbeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    List<Barbeiro> findAllByAtivoTrue();
}
