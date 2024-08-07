package com.princeton.sistemaBancario.api.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BancoInput {
	
	@ApiModelProperty(example = "1")
	@NotNull
	private Long id;
}
