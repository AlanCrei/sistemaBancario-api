package com.princeton.sistemaBancario.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.princeton.sistemaBancario.domain.model.Conta;
import com.princeton.sistemaBancario.domain.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, UUID> , JpaSpecificationExecutor<Transferencia>{
	
	 boolean existsByOrigemOrDestino(Conta origem, Conta destino);
}
