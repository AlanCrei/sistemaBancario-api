package com.princeton.sistemaBancario.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContaInput {
	
	@ApiModelProperty(position = 1)
	@Valid
	@NotNull
    private BancoInput banco;

	@ApiModelProperty(example = "12886-4", position = 2)
    @NotBlank
    @Pattern(regexp = "\\d{5}-\\d")
    private String conta;

	@ApiModelProperty(example = "Alan Crei", position = 3)
    @NotBlank
    private String beneficiario;

	@ApiModelProperty(example = "2000", position = 4)
    @DecimalMin(value = "1000.00")
    @NotNull
    private BigDecimal saldo;	
}
