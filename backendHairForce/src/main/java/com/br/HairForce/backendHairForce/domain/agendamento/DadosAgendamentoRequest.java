package com.br.HairForce.backendHairForce.domain.agendamento;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DadosAgendamentoRequest(
        @NotBlank
        String hora,
        @NotBlank
        List<Servico> servico,
        @NotBlank
        Long barbeiroId) {
}
