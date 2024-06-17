package com.princeton.sistemaBancario.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.princeton.sistemaBancario.domain.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	@Query("from Conta c join fetch c.banco")
	List<Conta> findAll();
	
	 @Query("SELECT COUNT(c) > 0 FROM Conta c WHERE c.banco.id = :bancoId AND c.conta = :numeroConta")
	 boolean isContaUnica(Long bancoId, String numeroConta);
}
