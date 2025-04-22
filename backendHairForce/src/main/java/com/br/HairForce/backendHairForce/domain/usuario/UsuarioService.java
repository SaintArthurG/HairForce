package com.br.HairForce.backendHairForce.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(DadosCadastroUsuario dados){
        String senhaCriptografada = encoder.encode(dados.senha());

        var usuario = new Usuario(dados.nome(), dados.email(), senhaCriptografada);
        return usuarioRepository.save(usuario);
    }


}
