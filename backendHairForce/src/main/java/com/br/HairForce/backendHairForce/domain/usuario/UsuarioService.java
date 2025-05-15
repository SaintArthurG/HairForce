package com.br.HairForce.backendHairForce.domain.usuario;

import com.br.HairForce.backendHairForce.domain.service.EmailSendService;
import com.br.HairForce.backendHairForce.infra.security.TokenService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailSendService emailSendService;

    @Autowired
    private TokenService tokenService;

    public Usuario criarUsuario(DadosCadastroUsuario dados){
        String senhaCriptografada = encoder.encode(dados.senha());

        var usuario = new Usuario(dados.nome(), dados.email(), senhaCriptografada, null);
        return usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email){
        var usuario = usuarioRepository.findByEmail(email);
        return (Usuario) usuario;
    }

    public String forgotPassword(String email) {
        var usuario = usuarioRepository.findByEmail(email);
        var tokenReset = tokenService.gerarToken((Usuario) usuario);
        System.out.println(tokenReset);
        try {
            emailSendService.sendSetPasswordEmail(email, tokenReset);
        } catch (MessagingException e){
            throw new RuntimeException("Unable to send set password email please try again" + e.getMessage() + email + usuario);
        }
        return "Check your email to set new password to yout account " + email;
    }


    public String setNewPassword(String token, String novaSenha) {
        var emailUsuario = tokenService.getSubject(token);
        System.out.println(emailUsuario);
        UserDetails userDetails = usuarioRepository.findByEmail(emailUsuario);

        if(!(userDetails instanceof Usuario usuario)){
            throw new IllegalStateException("Usuario invalido");
        }

        String senhaCriptografada = encoder.encode(novaSenha);
        usuario.atualizarSenha(senhaCriptografada);
        usuarioRepository.save(usuario);

        return "Nova senha alterada com sucesso";
    }
}
