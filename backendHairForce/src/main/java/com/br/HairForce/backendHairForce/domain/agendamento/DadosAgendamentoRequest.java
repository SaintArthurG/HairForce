package com.br.HairForce.backendHairForce.domain.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DadosAgendamentoRequest(
        @NotBlank
        String hora,

        @NotBlank
        List<Servico> servico,

        Long barbeiroId) {
}
