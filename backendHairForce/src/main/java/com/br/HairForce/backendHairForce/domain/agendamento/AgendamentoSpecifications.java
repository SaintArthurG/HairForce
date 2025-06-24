package com.br.HairForce.backendHairForce.domain.agendamento;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class AgendamentoSpecifications {

    public static Specification<Agendamento> barbeiroIdEquals(Long barbeiroId) {
        return (root, query, cb) ->
                barbeiroId == null ? null : cb.equal(root.get("barbeiro").get("id"), barbeiroId);
    }

    public static Specification<Agendamento> dataBetween(LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> {
            if (dataInicio != null && dataFim != null) {
                return criteriaBuilder.between(root.get("data"), dataInicio.atStartOfDay(), dataFim.atTime(23, 59, 59));
            } else if (dataInicio != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("data"), dataInicio.atStartOfDay());
            } else if (dataFim != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("data"), dataFim.atTime(23, 59, 59));
            } else {
                return null;
            }
        };
    }

    public static Specification<Agendamento> ativoEquals(Boolean ativo) {
        return (root, query, cb) ->
                ativo == null ? null : cb.equal(root.get("ativo"), ativo);
    }
}

