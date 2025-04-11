package com.br.HairForce.backendHairForce.usuario;

public record DadosDetalhamentoUsuario (Long id, String nome, String email){

    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
