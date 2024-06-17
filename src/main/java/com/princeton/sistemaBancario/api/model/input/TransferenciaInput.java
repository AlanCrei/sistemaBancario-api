package com.princeton.sistemaBancario.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferenciaInput {
	
	@Valid
	@NotNull
    private ContaInputId contaOrigem;

	@Valid
    @NotNull
    private ContaInputId ContaDestino;

    @NotNull
    private BigDecimal valor;
}
