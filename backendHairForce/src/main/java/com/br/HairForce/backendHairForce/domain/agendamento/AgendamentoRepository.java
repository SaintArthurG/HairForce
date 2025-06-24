package com.br.HairForce.backendHairForce.domain.agendamento;

import com.br.HairForce.backendHairForce.domain.barbeiro.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>, JpaSpecificationExecutor<Agendamento> {
    boolean existsByBarbeiroAndData(Barbeiro barbeiro, LocalDateTime data);

    List<Agendamento> findAllByAtivoTrue();

    List<Agendamento> findByUsuarioIdAndAtivoTrue(Long usuarioId);


//    @Query("SELECT a FROM Agendamento a WHERE a.barbeiro.id = :barbeiroId")
//    List<Agendamento> findByBarbeiroIdAndAgendamentoAtivoTrue(Long barbeiroId);

}
