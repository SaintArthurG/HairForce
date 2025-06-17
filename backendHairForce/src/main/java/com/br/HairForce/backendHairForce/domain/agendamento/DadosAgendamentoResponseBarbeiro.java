package com.br.HairForce.backendHairForce.domain.agendamento;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record DadosAgendamentoResponseBarbeiro(
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,
        List<Servico> servicos,
        String nomeBarbeiro,
        String nomeUsuario) {
    public DadosAgendamentoResponseBarbeiro(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getData(), agendamento.getServico(), agendamento.getBarbeiro().getNome(), agendamento.getUsuario().getNome());
    }
}
