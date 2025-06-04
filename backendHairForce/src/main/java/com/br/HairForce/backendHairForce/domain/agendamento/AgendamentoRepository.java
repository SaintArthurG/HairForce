package com.br.HairForce.backendHairForce.domain.agendamento;

import com.br.HairForce.backendHairForce.domain.barbeiro.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByBarbeiroAndData(Barbeiro barbeiro, LocalDateTime data);
    List<Agendamento> findAllByAtivoTrue();

    List<Agendamento> findByUsuarioIdAndAtivoTrue(Long usuarioId);
}
