package com.princeton.sistemaBancario.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.princeton.sistemaBancario.api.converter.TransferenciaConverter;
import com.princeton.sistemaBancario.api.model.TransferenciaModel;
import com.princeton.sistemaBancario.api.model.input.TransferenciaInput;
import com.princeton.sistemaBancario.domain.exception.ContaNaoEncontradaException;
import com.princeton.sistemaBancario.domain.exception.NegocioException;
import com.princeton.sistemaBancario.domain.model.Conta;
import com.princeton.sistemaBancario.domain.model.Transferencia;
import com.princeton.sistemaBancario.domain.repository.ContaRepository;
import com.princeton.sistemaBancario.domain.repository.TransferenciaRepository;
import com.princeton.sistemaBancario.infrastructure.repository.spec.TransferenciaSpec;

@Service
public class TransferenciaService {
	
	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	TransferenciaRepository transferenciaRepository;
	
	@Autowired
	TransferenciaConverter transferenciaConverter;
	
	@Transactional
    public TransferenciaModel criarTransferencia(TransferenciaInput transferenciaInput) {
		
        // Recupera as contas de origem e destino pelo ID
        Conta origem = contaRepository.findById(transferenciaInput.getContaOrigem().getId())
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de origem não encontrada"));
        Conta destino = contaRepository.findById(transferenciaInput.getContaDestino().getId())
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de destino não encontrada"));

        // Valida se a conta de origem e destino não são iguais
        if (origem.getId().equals(destino.getId())) {
            throw new NegocioException("A conta de origem e destino não podem ser iguais");
        }

        // Valida se o valor é maior que zero
        if (transferenciaInput.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegocioException("O valor da transferência deve ser maior que zero");
        }

        // Verifica se há saldo suficiente na conta de origem
        if (origem.getSaldo().compareTo(transferenciaInput.getValor()) < 0) {
            throw new NegocioException("Saldo insuficiente na conta de origem");
        }

        // Realiza a transferência
        origem.setSaldo(origem.getSaldo().subtract(transferenciaInput.getValor()));
        destino.setSaldo(destino.getSaldo().add(transferenciaInput.getValor()));

        // Salva as contas atualizadas
        contaRepository.save(origem);
        contaRepository.save(destino);

        // Cria a transferência e salva
        Transferencia transferencia = new Transferencia();
        transferencia.setOrigem(origem);
        transferencia.setDestino(destino);
        transferencia.setValor(transferenciaInput.getValor());
        transferenciaRepository.save(transferencia);
           
        return  transferenciaConverter.toModel(transferencia);
    }
	
	
	@Transactional(readOnly = true)
    public Page<TransferenciaModel> listarTransferencias(Long idContaOrigem, Long idContaDestino, 
    		LocalDate dataInicial, LocalDate dataFinal, Pageable pageable) {
		
        Page<Transferencia> transferencias = transferenciaRepository.findAll(
        		TransferenciaSpec.filtroPorContaEDatas(idContaOrigem, idContaDestino, dataInicial, dataFinal), pageable);
        
        return transferencias.map(transferencia -> transferenciaConverter.toModel(transferencia));
    }
}
