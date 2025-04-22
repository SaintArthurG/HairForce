package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.domain.agendamento.AgendamentoService;
import com.br.HairForce.backendHairForce.domain.agendamento.DadosAgendamentoRequest;
import com.br.HairForce.backendHairForce.domain.agendamento.DadosAgendamentoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<DadosAgendamentoResponse> criarAgendamento(@RequestBody DadosAgendamentoRequest dados){
        var response = agendamentoService.criarAgendamento(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
