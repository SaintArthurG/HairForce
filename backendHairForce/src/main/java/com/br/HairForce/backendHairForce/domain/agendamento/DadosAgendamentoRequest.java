package com.br.HairForce.backendHairForce.domain.agendamento;

import jakarta.validation.constraints.NotBlank;

public record DadosAgendamentoRequest(
        @NotBlank
        String hora,
        @NotBlank
        Servico servico,
        @NotBlank
        Long barbeiroId) {
}
