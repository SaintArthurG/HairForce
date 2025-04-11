package com.br.HairForce.backendHairForce.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank
        String senha
        ) {

}
