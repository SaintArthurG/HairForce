package com.br.HairForce.backendHairForce.domain.barbeiro;

import com.br.HairForce.backendHairForce.domain.service.EmailSendService;
import com.br.HairForce.backendHairForce.domain.usuario.DadosCadastroUsuario;
import com.br.HairForce.backendHairForce.domain.usuario.Usuario;
import com.br.HairForce.backendHairForce.domain.usuario.UsuarioRepository;
import com.br.HairForce.backendHairForce.domain.usuario.UsuarioService;
import com.br.HairForce.backendHairForce.infra.security.TokenService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.Matchers.any;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock private PasswordEncoder encoder;
    @Mock private UsuarioRepository usuarioRepository;
    @Mock private EmailSendService emailSendService;
    @Mock private TokenService tokenService;

    @InjectMocks private UsuarioService usuarioService;


    @Test
    void deveCriarNovoUsuarioComSenhaCriptografada() {
        DadosCadastroUsuario dados = new DadosCadastroUsuario("Arthur", "arthur@email.com", "123456");

        when(encoder.encode("123456")).thenReturn("senha_criptografada");
        when(usuarioRepository.save(ArgumentMatchers.<Usuario>any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Usuario usuarioCriado = usuarioService.criarUsuario(dados);

        assertEquals("Arthur", usuarioCriado.getNome());
        assertEquals("arthur@email.com", usuarioCriado.getEmail());
        assertEquals("senha_criptografada", usuarioCriado.getSenha());
    }

    @Test
    void deveRetornarUsuarioPorEmail() {
        Usuario usuario = new Usuario("João", "joao@email.com", "senha", null);

        when(usuarioRepository.findByEmail("joao@email.com")).thenReturn(usuario);

        Usuario resultado = usuarioService.findByEmail("joao@email.com");

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
    }

    @Test
    void deveEnviarEmailComTokenQuandoSolicitarResetDeSenha() throws Exception {
        Usuario usuario = new Usuario("Maria", "maria@email.com", "senha", null);

        when(usuarioRepository.findByEmail("maria@email.com")).thenReturn(usuario);
        when(tokenService.gerarToken(usuario)).thenReturn("token123");

        String resposta = usuarioService.forgotPassword("maria@email.com");

        verify(emailSendService).sendSetPasswordEmail("maria@email.com", "token123");
        assertTrue(resposta.contains("Check your email"));
    }

    @Test
    void deveLancarExcecaoSeFalharAoEnviarEmailReset() throws Exception {
        Usuario usuario = new Usuario("Lucas", "lucas@email.com", "senha", null);

        when(usuarioRepository.findByEmail("lucas@email.com")).thenReturn(usuario);
        when(tokenService.gerarToken(usuario)).thenReturn("token123");
        doThrow(new MessagingException("Erro")).when(emailSendService).sendSetPasswordEmail(anyString(), anyString());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                usuarioService.forgotPassword("lucas@email.com")
        );

        assertTrue(ex.getMessage().contains("Unable to send set password email"));
    }

    @Test
    void deveAtualizarSenhaComTokenValido() {
        Usuario usuario = new Usuario("Carlos", "carlos@email.com", "senha_antiga", null);

        when(tokenService.getSubject("tokenValido")).thenReturn("carlos@email.com");
        when(usuarioRepository.findByEmail("carlos@email.com")).thenReturn(usuario);
        when(encoder.encode("novaSenha")).thenReturn("novaSenhaCriptografada");

        String resposta = usuarioService.setNewPassword("tokenValido", "novaSenha");

        assertEquals("Nova senha alterada com sucesso", resposta);
        verify(usuarioRepository).save(usuario);
        assertEquals("novaSenhaCriptografada", usuario.getSenha());
    }


}
