package com.br.HairForce.backendHairForce.domain.usuario;

import com.br.HairForce.backendHairForce.domain.service.EmailSendService;
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
        try {
            emailSendService.sendSetPasswordEmail(email);
            System.out.println(usuario);
        } catch (MessagingException e){
            throw new RuntimeException("Unable to send set password email please try again" + e.getMessage() + email + usuario);
        }
        return "Check your email to set new password to yout account";
    }


    public String setNewPassword(String email, String novaSenha) {
        UserDetails userDetails = usuarioRepository.findByEmail(email);

        if(!(userDetails instanceof Usuario usuario)){
            throw new IllegalStateException("Usuario invalido");
        }

        String senhaCriptografada = encoder.encode(novaSenha);
        usuario.atualizarSenha(senhaCriptografada);
        usuarioRepository.save(usuario);

        return "Nova senha alterada com sucesso";
    }
}
