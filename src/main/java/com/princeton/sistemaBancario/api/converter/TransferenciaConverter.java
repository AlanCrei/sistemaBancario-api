package com.princeton.sistemaBancario.api.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.princeton.sistemaBancario.api.model.TransferenciaModel;
import com.princeton.sistemaBancario.domain.model.Transferencia;

@Component
public class TransferenciaConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public TransferenciaModel toModel(Transferencia transferencia) {
		return modelMapper.map(transferencia, TransferenciaModel.class);
	}
}
