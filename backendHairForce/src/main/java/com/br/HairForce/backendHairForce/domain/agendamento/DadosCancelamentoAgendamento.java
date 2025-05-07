package com.br.HairForce.backendHairForce.domain.agendamento;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoAgendamento(
        @NotNull
        Long idAgendamento,

        @NotNull
        MotivoCancelamento motivo) {
}
