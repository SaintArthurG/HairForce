package com.br.HairForce.backendHairForce.domain.agendamento;

import com.br.HairForce.backendHairForce.domain.barbeiro.Barbeiro;
import com.br.HairForce.backendHairForce.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


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

    @ElementCollection(targetClass = Servico.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "agendamento_servicos",
            joinColumns = @JoinColumn(name = "agendamento_id")
    )
    @Column(name = "servico")
    private List<Servico> servico;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime data;

    private Boolean ativo;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public Agendamento (DadosAgendamentoRequest dados, Barbeiro barbeiro, Usuario usuario){
        this.ativo = true;
        this.servico = dados.servico();
        this.data = dados.data();
        this.barbeiro = barbeiro;
        this.usuario = usuario;
        this.motivoCancelamento = null;
    }

    public void desativarAgendamento(MotivoCancelamento motivo){
        this.ativo = false;
        this.motivoCancelamento = motivo;
    }
}
