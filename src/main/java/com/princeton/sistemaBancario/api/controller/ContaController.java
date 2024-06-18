package com.princeton.sistemaBancario.api.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.princeton.sistemaBancario.api.converter.ContaInputConverter;
import com.princeton.sistemaBancario.api.converter.ContaModelConverter;
import com.princeton.sistemaBancario.api.model.ContaModel;
import com.princeton.sistemaBancario.api.model.input.ContaInput;
import com.princeton.sistemaBancario.domain.exception.BancoNaoEncontradoException;
import com.princeton.sistemaBancario.domain.exception.NegocioException;
import com.princeton.sistemaBancario.domain.model.Conta;
import com.princeton.sistemaBancario.domain.repository.ContaRepository;
import com.princeton.sistemaBancario.domain.service.ContaService;

@RestController
@RequestMapping(value = "/contas")
public class ContaController{
	
	@Autowired
	ContaInputConverter contaInputConverter;
	
	@Autowired
	ContaModelConverter contaModelConverter;
	
	@Autowired
	ContaService contaService;
	
	@Autowired
	ContaRepository contaRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('ROLE_CONTA_LST')")
	public Page<ContaModel>  listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Conta> ContasPage = contaRepository.findAll(pageable);
		
		List<ContaModel> contaModel = contaModelConverter
				.toCollectionModel(ContasPage.getContent());

		Page<ContaModel> contaModelPage = new PageImpl<>(contaModel, pageable,
				ContasPage.getTotalElements());
	    
		return contaModelPage;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyAuthority('ROLE_CONTA_ADD')")
	public ContaModel adicionar(@RequestBody @Valid  ContaInput contaInput) {
		contaService.validarContaUnica(contaInput.getBanco().getId(), contaInput.getConta());
		Conta conta = contaInputConverter.toDomain(contaInput);
		conta = contaService.salvar(conta);
		
		return contaModelConverter.toModel(conta);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_CONTA_UPD')")
	public ContaModel atualizar(@PathVariable Long id, @RequestBody @Valid ContaInput contaInput) {
		try {
			Conta contaAtual = contaService.buscarOuFalhar(id);
			contaInputConverter.copyToDomain(contaInput, contaAtual);
			contaAtual = contaService.salvar(contaAtual);
			
			return contaModelConverter.toModel(contaAtual);
		} catch (BancoNaoEncontradoException  e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{contaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAnyAuthority('ROLE_CONTA_DEL')")
	public void remover(@PathVariable Long contaId) {
		contaService.excluir(contaId);
	}
}
