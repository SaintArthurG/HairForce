package com.br.HairForce.backendHairForce.domain.agendamento;

import com.br.HairForce.backendHairForce.domain.barbeiro.Barbeiro;
import com.br.HairForce.backendHairForce.domain.barbeiro.BarbeiroRepository;
import com.br.HairForce.backendHairForce.domain.usuario.UsuarioRepository;
import com.br.HairForce.backendHairForce.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.br.HairForce.backendHairForce.domain.agendamento.AgendamentoSpecifications.*;


@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AgendamentoService(
            AgendamentoRepository agendamentoRepository,
            BarbeiroRepository barbeiroRepository,
            UsuarioRepository usuarioRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.barbeiroRepository = barbeiroRepository;
        this.usuarioRepository = usuarioRepository;
    }


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

        boolean existeConflito = agendamentoRepository.existsByBarbeiroAndData(barbeiro, dados.dataHorario());

        if(existeConflito){
            throw new RuntimeException("Já existe agendamento para esse barbeiro nesse horario");
        }

        var agendamento = new Agendamento(dados.dataHorario(), dados.servico(), barbeiro, usuario);
        agendamentoRepository.save(agendamento);

        return new DadosAgendamentoResponse(agendamento);
    }

    public List<DadosAgendamentoResponseBarbeiro> listarTodosAgendamentos(){
        var agendamentos = agendamentoRepository.findAllByAtivoTrue();
        return agendamentos.stream().map(DadosAgendamentoResponseBarbeiro::new).toList();
    }

    public List<DadosAgendamentoResponse> listarAgendamentosPorUsuario(Long usuarioId){
        var agendamentos = agendamentoRepository.findByUsuarioIdAndAtivoTrue(usuarioId);
        return agendamentos.stream().map(DadosAgendamentoResponse::new).toList();
    }

//    public List<DadosAgendamentoResponseBarbeiro> listarAgendamentosPorBarbeiro(Long barbeiroId){
//        var agendamentos = agendamentoRepository.findByBarbeiroIdAndAgendamentoAtivoTrue(barbeiroId);
//        return agendamentos.stream().map(DadosAgendamentoResponseBarbeiro::new).toList();
//    }

    public List<DadosAgendamentoResponseBarbeiro> buscarComFiltros(Long barbeiroId, LocalDate dataInicio, LocalDate dataFim, Boolean ativo) {
        Specification<Agendamento> spec = Specification
                .where(barbeiroIdEquals(barbeiroId))
                .and(dataBetween(dataInicio, dataFim))
                .and(ativoEquals(ativo));

        return agendamentoRepository.findAll(spec)
                .stream()
                .map(DadosAgendamentoResponseBarbeiro::new)
                .toList();
    }




    private Barbeiro escolherBarbeiro(DadosAgendamentoRequest dados){
        if(dados.barbeiroId() != null){
            return barbeiroRepository.getReferenceById(dados.barbeiroId());
        }
        return barbeiroRepository.encontrarBarbeiroLivre(dados.dataHorario());
    }

    public void cancelar(DadosCancelamentoAgendamento dados){
        if (!agendamentoRepository.existsById(dados.idAgendamento())) {
            throw new ValidacaoException("Id do agendamento não existe");
        }
        var agendamento = agendamentoRepository.getReferenceById(dados.idAgendamento());
        agendamento.desativarAgendamento(dados.motivo());
    }



}
