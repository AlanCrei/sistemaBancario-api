package com.princeton.sistemaBancario.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.princeton.sistemaBancario.domain.exception.ContaNaoEncontradaException;
import com.princeton.sistemaBancario.domain.exception.EntidadeEmUsoException;
import com.princeton.sistemaBancario.domain.exception.NegocioException;
import com.princeton.sistemaBancario.domain.model.Banco;
import com.princeton.sistemaBancario.domain.model.Conta;
import com.princeton.sistemaBancario.domain.repository.ContaRepository;
import com.princeton.sistemaBancario.domain.repository.TransferenciaRepository;

@Service
public class ContaService {
	
	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	TransferenciaRepository transferenciaRepository;
	
	@Autowired
	BancoService bancoService;
	
	@Transactional
	public Conta salvar(Conta conta) {
		Long bancoId = conta.getBanco().getId();
		Banco banco = bancoService.buscarOuFalhar(bancoId);
		conta.setBanco(banco);
	
		return contaRepository.save(conta);
	}
	
	@Transactional
	public void excluir(Long contaId) {
		Conta conta = buscarOuFalhar(contaId);
        boolean existeTransferencias = transferenciaRepository.existsByOrigemOrDestino(conta, conta);

        if (existeTransferencias) {
            throw new EntidadeEmUsoException("Não é possível remover a conta pois há transferências associadas a ela.");
        }
		
		contaRepository.deleteById(contaId);
		contaRepository.flush();	
	}
	
	public Conta buscarOuFalhar(Long idConta) {
		return contaRepository.findById(idConta)
			.orElseThrow(() -> new ContaNaoEncontradaException(idConta));
	}
	
	public void validarContaUnica(Long idBanco, String numeroConta) {
		boolean contaExistente = contaRepository.isContaUnica(idBanco, numeroConta);
        
        if (contaExistente) {
            throw new NegocioException("Já existe uma conta com este banco e número de conta");
        }
    }
}
