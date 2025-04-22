package com.br.HairForce.backendHairForce.domain.agendamento;

public record DadosAgendamentoResponse(Long id, String hora, Servico servico, String nomeBarbeiro) {
    public DadosAgendamentoResponse(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getHora(), agendamento.getServico(), agendamento.getBarbeiro().getNome());
    }
}
