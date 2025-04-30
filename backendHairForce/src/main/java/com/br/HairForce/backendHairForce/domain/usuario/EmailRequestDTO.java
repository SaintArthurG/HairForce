package com.br.HairForce.backendHairForce.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequestDTO(
        @NotBlank @Email
        String email
        ) {

}
