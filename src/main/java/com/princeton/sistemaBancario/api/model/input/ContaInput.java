package com.princeton.sistemaBancario.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContaInput {
	
	@Valid
	@NotNull
    private BancoInput banco;

    @NotBlank
    @Pattern(regexp = "\\d{5}-\\d")
    private String conta;

    @NotBlank
    private String beneficiario;

    @DecimalMin(value = "1000.00")
    @NotNull
    private BigDecimal saldo;	
}
