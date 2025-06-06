package com.br.HairForce.backendHairForce.domain.barbeiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    List<Barbeiro> findAllByAtivoTrue();

    @Query("""
            SELECT b from Barbeiro b
            WHERE
            b.ativo = true
            AND
            b.id NOT IN(
                SELECT a.barbeiro.id from Agendamento a
                WHERE
                a.data = :data
            )
            ORDER BY rand()
            limit 1
            """)
    Barbeiro encontrarBarbeiroLivre(LocalDateTime data);
}
