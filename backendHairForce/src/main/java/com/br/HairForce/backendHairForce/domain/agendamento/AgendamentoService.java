package com.br.HairForce.backendHairForce.domain.agendamento;

import com.br.HairForce.backendHairForce.domain.barbeiro.Barbeiro;
import com.br.HairForce.backendHairForce.domain.barbeiro.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    public DadosAgendamentoResponse criarAgendamento(DadosAgendamentoRequest dados){
        Barbeiro barbeiro = barbeiroRepository.findById(dados.barbeiroId())
                .orElseThrow(()-> new RuntimeException("Barbeiro não encontrado"));

        boolean existeConflito = agendamentoRepository.existsByBarbeiroAndHora(barbeiro, dados.hora());

        if(existeConflito){
            throw new RuntimeException("Já existe agendamento para esse barbeiro nesse horario");
        }

        var agendamento = new Agendamento(dados, barbeiro);
        agendamentoRepository.save(agendamento);

        return new DadosAgendamentoResponse(agendamento);
    }


}
