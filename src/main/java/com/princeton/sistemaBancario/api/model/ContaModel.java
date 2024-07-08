package com.princeton.sistemaBancario.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContaModel {
	
	@ApiModelProperty(example = "1", position = 1)
    private Long id;
	
	@ApiModelProperty(position = 2)
    private BancoModel banco;

    @ApiModelProperty(example = "12886-3", position = 3)
    private String conta;

    @ApiModelProperty(example = "Maria Aparecida", position = 4)
    private String beneficiario;

    @ApiModelProperty(example = "2000", position = 5)
    private BigDecimal saldo;
}
