package com.br.HairForce.backendHairForce.domain.agendamento;

import com.br.HairForce.backendHairForce.domain.barbeiro.Barbeiro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "agendamentos")
@Entity(name = "Agendamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    private String hora;

    private Boolean ativo;

    public Agendamento (DadosAgendamentoRequest dados, Barbeiro barbeiro){
        this.ativo = true;
        this.servico = dados.servico();
        this.hora = dados.hora();
        this.barbeiro = barbeiro;
    }

    public void desativarAgendamento(){
        this.ativo = false;
    }
}
