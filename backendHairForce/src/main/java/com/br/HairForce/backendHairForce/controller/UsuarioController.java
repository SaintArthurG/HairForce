package com.br.HairForce.backendHairForce.controller;

import com.br.HairForce.backendHairForce.domain.service.EmailSendService;
import com.br.HairForce.backendHairForce.domain.usuario.*;
import com.br.HairForce.backendHairForce.infra.security.TokenService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailSendService sendService;


    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.criarUsuario(dados);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        usuario.excluirUsuario();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/resetarSenha")
    public ResponseEntity forgotPassword(@RequestBody EmailRequestDTO dados) {
        return new ResponseEntity<>(usuarioService.forgotPassword(dados.email()), HttpStatus.OK);
    }

    @PutMapping("/set-password")
    public ResponseEntity<String> setNewPassword(@RequestParam("token") String token, @RequestBody SetSenhaRequest dados){
        return new ResponseEntity<>(usuarioService.setNewPassword(token, dados.novaSenha()), HttpStatus.OK);
    }


}
