package com.br.HairForce.backendHairForce.domain.barbeiro;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroBarbeiro(
        @NotBlank
        String nome
) {
}
