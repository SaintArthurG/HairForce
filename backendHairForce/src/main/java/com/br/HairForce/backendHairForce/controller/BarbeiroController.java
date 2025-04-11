package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.barbeiro.Barbeiro;
import com.br.HairForce.backendHairForce.barbeiro.BarbeiroRepository;
import com.br.HairForce.backendHairForce.barbeiro.DadosCadastroBarbeiro;
import com.br.HairForce.backendHairForce.barbeiro.DadosDetalhamentoBarbeiro;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/barbeiros")
public class BarbeiroController {

    @Autowired
    private BarbeiroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarBarbeiro(@RequestBody @Valid DadosCadastroBarbeiro dados, UriComponentsBuilder uriBuilder){
        var barbeiro = new Barbeiro(dados);
        repository.save(barbeiro);
        var uri = uriBuilder.path("/barbeiros/{id}").buildAndExpand(barbeiro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoBarbeiro(barbeiro));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoBarbeiro>> listarBarbeiro(){
        var barbeiros = repository.findAllByAtivoTrue();
        var dto = barbeiros.stream()
                .map(DadosDetalhamentoBarbeiro::new).toList();
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirBarbeiro(@PathVariable Long id){
        var barbeiro = repository.getReferenceById(id);
        barbeiro.excuirBarbeiro();

        return ResponseEntity.noContent().build();

    }
}
