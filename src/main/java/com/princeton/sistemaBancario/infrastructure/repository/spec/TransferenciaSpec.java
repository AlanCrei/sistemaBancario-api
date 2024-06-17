package com.princeton.sistemaBancario.infrastructure.repository.spec;

import java.time.LocalDate;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.princeton.sistemaBancario.domain.model.Transferencia;

public class TransferenciaSpec {

	public static Specification<Transferencia> filtroPorContaEDatas(Long idContaOrigem, Long idContaDestino, LocalDate dataInicial, LocalDate dataFinal) {
        return (root, query, criteriaBuilder) -> {
        	
            Predicate predicate = criteriaBuilder.conjunction();

            if (idContaOrigem != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("origem").get("id"), idContaOrigem));
            }

            if (idContaDestino != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("destino").get("id"), idContaDestino));
            }

            if (dataInicial != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("data"), dataInicial));
            }

            if (dataFinal != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("data"), dataFinal));
            }

            return predicate;
        };
    }
}
