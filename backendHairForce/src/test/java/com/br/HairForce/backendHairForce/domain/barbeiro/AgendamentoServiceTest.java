package com.br.HairForce.backendHairForce.domain.barbeiro;

import com.br.HairForce.backendHairForce.domain.agendamento.*;
import com.br.HairForce.backendHairForce.domain.usuario.Usuario;
import com.br.HairForce.backendHairForce.domain.usuario.UsuarioRepository;
import com.br.HairForce.backendHairForce.exception.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgendamentoServiceTest {

    private AgendamentoRepository agendamentoRepository;
    private BarbeiroRepository barbeiroRepository;
    private UsuarioRepository usuarioRepository;
    private AgendamentoService service;

    private final List<Servico> SERVICOS = List.of(Servico.CORTAR_CABELO);


    @BeforeEach
    void setUp() {
        agendamentoRepository = mock(AgendamentoRepository.class);
        barbeiroRepository = mock(BarbeiroRepository.class);
        usuarioRepository = mock(UsuarioRepository.class);
        service = new AgendamentoService(agendamentoRepository, barbeiroRepository, usuarioRepository);
    }

    @Test
    void deveCriarAgendamentoComSucesso() {
        Long usuarioId = 1L;
        Long barbeiroId = 2L;
        LocalDateTime data = LocalDateTime.now().plusDays(1);
        var dados = new DadosAgendamentoRequest(data, SERVICOS, barbeiroId);

        var barbeiro = mock(Barbeiro.class);
        var usuario = mock(Usuario.class);

        when(usuarioRepository.existsById(usuarioId)).thenReturn(true);
        when(barbeiroRepository.existsById(barbeiroId)).thenReturn(true);
        when(barbeiroRepository.getReferenceById(barbeiroId)).thenReturn(barbeiro);
        when(usuarioRepository.getReferenceById(usuarioId)).thenReturn(usuario);
        when(agendamentoRepository.existsByBarbeiroAndData(barbeiro, data)).thenReturn(false);

        var response = service.criarAgendamento(dados, usuarioId);

        assertNotNull(response);
        verify(agendamentoRepository).save(any(Agendamento.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        Long usuarioId = 999L;
        var dados = new DadosAgendamentoRequest(LocalDateTime.now().plusDays(1), SERVICOS, null);

        when(usuarioRepository.existsById(usuarioId)).thenReturn(false);

        var excecao = assertThrows(ValidacaoException.class, () -> {
            service.criarAgendamento(dados, usuarioId);
        });

        assertEquals("Id do usuario invalido", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoBarbeiroInvalido() {
        Long usuarioId = 1L;
        Long barbeiroId = 2L;
        var dados = new DadosAgendamentoRequest(LocalDateTime.now().plusDays(1), SERVICOS, barbeiroId);

        when(usuarioRepository.existsById(usuarioId)).thenReturn(true);
        when(barbeiroRepository.existsById(barbeiroId)).thenReturn(false);

        var excecao = assertThrows(ValidacaoException.class, () -> {
            service.criarAgendamento(dados, usuarioId);
        });

        assertEquals("Id do barbeiro invalido", excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoSeConflitoDeHorario() {
        Long usuarioId = 1L;
        Long barbeiroId = 2L;
        LocalDateTime data = LocalDateTime.now().plusDays(1);
        var dados = new DadosAgendamentoRequest(data, SERVICOS, barbeiroId);

        var barbeiro = mock(Barbeiro.class);
        var usuario = mock(Usuario.class);

        when(usuarioRepository.existsById(usuarioId)).thenReturn(true);
        when(barbeiroRepository.existsById(barbeiroId)).thenReturn(true);
        when(barbeiroRepository.getReferenceById(barbeiroId)).thenReturn(barbeiro);
        when(usuarioRepository.getReferenceById(usuarioId)).thenReturn(usuario);
        when(agendamentoRepository.existsByBarbeiroAndData(barbeiro, data)).thenReturn(true);

        var excecao = assertThrows(RuntimeException.class, () -> {
            service.criarAgendamento(dados, usuarioId);
        });

        assertEquals("Já existe agendamento para esse barbeiro nesse horario", excecao.getMessage());
    }

    @Test
    void deveCancelarAgendamentoComSucesso() {
        Long agendamentoId = 10L;
        var motivoCancelamentos = MotivoCancelamento.OUTROS;
        var dadosCancelamento = new DadosCancelamentoAgendamento(agendamentoId, motivoCancelamentos);
        var agendamento = mock(Agendamento.class);

        when(agendamentoRepository.existsById(agendamentoId)).thenReturn(true);
        when(agendamentoRepository.getReferenceById(agendamentoId)).thenReturn(agendamento);

        service.cancelar(dadosCancelamento);

        verify(agendamento).desativarAgendamento(motivoCancelamentos);
    }
}

