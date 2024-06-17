package com.princeton.sistemaBancario.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContaInputId {
	
	@NotNull
	private Long id;
}
