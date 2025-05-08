package com.br.HairForce.backendHairForce.domain.agendamento;

import com.br.HairForce.backendHairForce.domain.barbeiro.Barbeiro;
import com.br.HairForce.backendHairForce.domain.barbeiro.BarbeiroRepository;
import com.br.HairForce.backendHairForce.domain.usuario.Usuario;
import com.br.HairForce.backendHairForce.domain.usuario.UsuarioRepository;
import com.br.HairForce.backendHairForce.exception.ValidacaoException;
import com.br.HairForce.backendHairForce.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public DadosAgendamentoResponse criarAgendamento(DadosAgendamentoRequest dados, Long usuarioId){

        //PRECISA PEGAR O ID DO USUARIO PELO BACKEND NAO ENVIAR DIRETO DO FRONT, NO CASO TEHNO QUE MUDAR O DTO
        if (!usuarioRepository.existsById(usuarioId)){
            throw new ValidacaoException("Id do usuario invalido");
        }

        if(dados.barbeiroId() != null && !barbeiroRepository.existsById(dados.barbeiroId())){
            throw new ValidacaoException("Id do barbeiro invalido");
        }

        var barbeiro = escolherBarbeiro(dados);

        var usuario = usuarioRepository.getReferenceById(usuarioId);

        boolean existeConflito = agendamentoRepository.existsByBarbeiroAndHora(barbeiro, dados.hora());

        if(existeConflito){
            throw new RuntimeException("Já existe agendamento para esse barbeiro nesse horario");
        }

        var agendamento = new Agendamento(dados, barbeiro, usuario);
        agendamentoRepository.save(agendamento);

        return new DadosAgendamentoResponse(agendamento);
    }

    public List<DadosAgendamentoResponse> listarAgendamentos(Usuario usuario){
        var agendamentos = agendamentoRepository.findByUsuario(usuario);
        return agendamentos.stream().map(DadosAgendamentoResponse::new).toList();
                //var dto = barbeiros.stream()
        //                .map(DadosDetalhamentoBarbeiro::new).toList();
        //        return ResponseEntity.ok(dto);
    }

    private Barbeiro escolherBarbeiro(DadosAgendamentoRequest dados){
        if(dados.barbeiroId() != null){
            return barbeiroRepository.getReferenceById(dados.barbeiroId());
        }

        return barbeiroRepository.encontrarBarbeiroLivre(dados.hora());
    }

    public void cancelar(DadosCancelamentoAgendamento dados){
        if (!agendamentoRepository.existsById(dados.idAgendamento())) {
            throw new ValidacaoException("Id do agendamento não existe");
        }
        var agendamento = agendamentoRepository.getReferenceById(dados.idAgendamento());
        agendamento.desativarAgendamento(dados.motivo());
    }
}
