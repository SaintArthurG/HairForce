package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.domain.agendamento.AgendamentoService;
import com.br.HairForce.backendHairForce.domain.agendamento.DadosAgendamentoRequest;
import com.br.HairForce.backendHairForce.domain.agendamento.DadosAgendamentoResponse;
import com.br.HairForce.backendHairForce.domain.agendamento.DadosCancelamentoAgendamento;
import com.br.HairForce.backendHairForce.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosAgendamentoResponse> criarAgendamento(@RequestBody DadosAgendamentoRequest dados, @AuthenticationPrincipal Usuario usuarioAutenticado){
        var usuarioId = usuarioAutenticado.getId();
        var response = agendamentoService.criarAgendamento(dados, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/meus-agendamentos")
    public ResponseEntity<List<DadosAgendamentoResponse>> listarAgendamentosPorUsuario(@AuthenticationPrincipal Usuario usuarioAutenticado){
        var usuarioId = usuarioAutenticado.getId();
        var response = agendamentoService.listarAgendamentosPorUsuario(usuarioId);
        System.out.println(response);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarAgendamento(@RequestBody @Valid DadosCancelamentoAgendamento dados){
        agendamentoService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_PICA')")
    public ResponseEntity<List<DadosAgendamentoResponse>> listarTodosAgendamentos(){
        var response = agendamentoService.listarTodosAgendamentos();
        return ResponseEntity.ok().body(response);
    }
}
