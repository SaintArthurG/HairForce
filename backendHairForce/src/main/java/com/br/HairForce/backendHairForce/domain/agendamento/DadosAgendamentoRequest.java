package com.br.HairForce.backendHairForce.domain.agendamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAgendamentoRequest(
        @NotNull
        Long idUsuario,

        @NotBlank
        String hora,

        @NotBlank
        List<Servico> servico,

        @NotBlank
        Long barbeiroId) {
}
