package com.br.HairForce.backendHairForce.domain.agendamento;

import java.time.LocalDateTime;
import java.util.List;

public record DadosAgendamentoResponse(Long id, String hora, List<Servico> servicos, String nomeBarbeiro) {
    public DadosAgendamentoResponse(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getHora(), agendamento.getServico(), agendamento.getBarbeiro().getNome());
    }
}
