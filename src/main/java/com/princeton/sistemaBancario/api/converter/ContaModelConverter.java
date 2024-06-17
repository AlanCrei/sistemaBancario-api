package com.princeton.sistemaBancario.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.princeton.sistemaBancario.api.model.ContaModel;
import com.princeton.sistemaBancario.domain.model.Conta;

@Component
public class ContaModelConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ContaModel toModel(Conta conta) {
		return modelMapper.map(conta, ContaModel.class);
	}
	
	public List<ContaModel> toCollectionModel(List<Conta> contas) {
		return contas.stream()
				.map(conta -> toModel(conta))
				.collect(Collectors.toList());
	}
}
