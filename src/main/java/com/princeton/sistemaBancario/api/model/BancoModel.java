package com.princeton.sistemaBancario.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BancoModel {
	
	@ApiModelProperty(example = "1", position = 1)
	private Long id;

	@ApiModelProperty(example = "001", position = 2)
    private String codigo;

	@ApiModelProperty(example = "Banco do Brasil", position = 3)
    private String nome;

}
