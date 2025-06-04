package com.br.HairForce.backendHairForce.domain.agendamento;

import java.time.LocalDateTime;
import java.util.List;

public record DadosAgendamentoResponseBarbeiro(Long id, LocalDateTime data, List<Servico> servicos, String nomeBarbeiro, String nomeUsuario) {
    public DadosAgendamentoResponseBarbeiro(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getData(), agendamento.getServico(), agendamento.getBarbeiro().getNome(), agendamento.getUsuario().getNome());
    }
}
