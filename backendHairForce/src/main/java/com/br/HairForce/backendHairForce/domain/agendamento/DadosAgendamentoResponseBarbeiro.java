package com.br.HairForce.backendHairForce.domain.agendamento;

import java.util.List;

public record DadosAgendamentoResponseBarbeiro(Long id, String hora, List<Servico> servicos, String nomeBarbeiro, String nomeUsuario) {
    public DadosAgendamentoResponseBarbeiro(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getHora(), agendamento.getServico(), agendamento.getBarbeiro().getNome(), agendamento.getUsuario().getNome());
    }
}
