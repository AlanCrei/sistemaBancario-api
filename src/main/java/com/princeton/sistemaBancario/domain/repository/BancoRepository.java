package com.princeton.sistemaBancario.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.princeton.sistemaBancario.domain.model.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

}
