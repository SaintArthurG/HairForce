package com.br.HairForce.backendHairForce.barbeiro;

public record DadosDetalhamentoBarbeiro(Long id, String nome) {
    public DadosDetalhamentoBarbeiro(Barbeiro barbeiro){
        this(barbeiro.getId(), barbeiro.getNome());
    }
}
