package com.br.HairForce.backendHairForce.barbeiro;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroBarbeiro(
        @NotBlank
        String nome
) {
}
