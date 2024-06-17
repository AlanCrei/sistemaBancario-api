package com.princeton.sistemaBancario.api.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.princeton.sistemaBancario.api.model.input.ContaInput;
import com.princeton.sistemaBancario.domain.model.Banco;
import com.princeton.sistemaBancario.domain.model.Conta;

@Component
public class ContaInputConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public Conta toDomain(ContaInput contaInput) {
		return modelMapper.map(contaInput, Conta.class);
	}
	
	public void copyToDomain(ContaInput contaInput, Conta conta) {
		// Para evitar erro org.hibernate.HibernateException: identifier of an instance of 
		//com.princeton.sistemaBancario.domain.model.Banco was altered from 2 to 1
		conta.setBanco(new Banco());
		
		modelMapper.map(contaInput, conta);
	}
}
