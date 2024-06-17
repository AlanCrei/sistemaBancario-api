package com.princeton.sistemaBancario.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.princeton.sistemaBancario.domain.exception.BancoNaoEncontradoException;
import com.princeton.sistemaBancario.domain.model.Banco;
import com.princeton.sistemaBancario.domain.repository.BancoRepository;

@Service
public class BancoService {
	
	@Autowired
	BancoRepository bancoRepository;
	
	public Banco buscarOuFalhar(Long bancoId) {
		return bancoRepository.findById(bancoId)
			.orElseThrow(() -> new BancoNaoEncontradoException(bancoId));
	}
}
