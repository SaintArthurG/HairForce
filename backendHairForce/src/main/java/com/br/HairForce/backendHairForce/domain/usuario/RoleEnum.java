package com.br.HairForce.backendHairForce.domain.usuario;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    PICA,
    BURACO;

    @Override
    public String getAuthority(){
        return "ROLE_" + this.name();
    }
}
