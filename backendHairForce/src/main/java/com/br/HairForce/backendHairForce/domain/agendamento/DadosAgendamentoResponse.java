package com.br.HairForce.backendHairForce.domain.agendamento;

import java.time.LocalDateTime;
import java.util.List;

public record DadosAgendamentoResponse(Long id, LocalDateTime data, List<Servico> servicos, String nomeBarbeiro) {
    public DadosAgendamentoResponse(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getData(), agendamento.getServico(), agendamento.getBarbeiro().getNome());
    }
}
