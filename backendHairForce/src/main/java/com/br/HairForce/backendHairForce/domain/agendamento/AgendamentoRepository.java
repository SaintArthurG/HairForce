package com.br.HairForce.backendHairForce.domain.agendamento;

import com.br.HairForce.backendHairForce.domain.barbeiro.Barbeiro;
import com.br.HairForce.backendHairForce.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByBarbeiroAndHora(Barbeiro barbeiro, String hora);
    List<Agendamento> findAllByAtivoTrue();

    List<Agendamento> findByUsuarioIdAndAtivoTrue(Long usuarioId);
}
