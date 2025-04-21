package com.br.HairForce.backendHairForce.domain.barbeiro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "barbeiros")
@Entity(name = "Barbeiro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Boolean ativo;

    public Barbeiro(DadosCadastroBarbeiro dados){
        this.ativo = true;
        this.nome = dados.nome();
    }

    public void excuirBarbeiro(){
        this.ativo = false;
    }

}
