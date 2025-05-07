package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.domain.agendamento.AgendamentoService;
import com.br.HairForce.backendHairForce.domain.agendamento.DadosAgendamentoRequest;
import com.br.HairForce.backendHairForce.domain.agendamento.DadosAgendamentoResponse;
import com.br.HairForce.backendHairForce.domain.agendamento.DadosCancelamentoAgendamento;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosAgendamentoResponse> criarAgendamento(@RequestBody DadosAgendamentoRequest dados){
        var response = agendamentoService.criarAgendamento(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DadosAgendamentoResponse>> listarAgendamentos(){
        var response = agendamentoService.listarAgendamentos();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarAgendamento(@RequestBody @Valid DadosCancelamentoAgendamento dados){
        agendamentoService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
