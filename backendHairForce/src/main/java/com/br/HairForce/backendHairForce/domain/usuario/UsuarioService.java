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

    public String forgotPassword(String email) {
        var usuario = getUsuarioByEmail(email);

        var tokenReset = tokenService.gerarToken((Usuario) usuario);
        try {
            emailSendService.sendSetPasswordEmail(email, tokenReset);
        } catch (MessagingException e){
            throw new RuntimeException("Unable to send set password email please try again" + e.getMessage() + email + usuario);
        }
        return "Check your email to set new password to yout account " + email;
    }


    public String setNewPassword(String token, String novaSenha) {
        var emailUsuario = tokenService.getSubject(token);

        var usuario = getUsuarioByEmail(emailUsuario);

        String senhaCriptografada = encoder.encode(novaSenha);
        usuario.atualizarSenha(senhaCriptografada);
        usuarioRepository.save(usuario);

        return "Nova senha alterada com sucesso";
    }

    private Usuario getUsuarioByEmail(String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalStateException("Usuário invalido"));
    }
}
