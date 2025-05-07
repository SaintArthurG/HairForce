package com.br.HairForce.backendHairForce.domain.barbeiro;

import com.br.HairForce.backendHairForce.domain.agendamento.Agendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "barbeiro")
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Barbeiro(DadosCadastroBarbeiro dados){
        this.ativo = true;
        this.nome = dados.nome();
    }

    public void excuirBarbeiro(){
        this.ativo = false;
    }

}
