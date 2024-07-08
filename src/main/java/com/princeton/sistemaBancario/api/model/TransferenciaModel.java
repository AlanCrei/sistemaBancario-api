package com.princeton.sistemaBancario.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferenciaModel {
	
	@ApiModelProperty(example = "be1248d2-7f14-4b05-af76-82ca918c9d68", position = 1)
	private UUID id;
	
	@ApiModelProperty(example = "2024-07-05", position = 2)
    private LocalDate data;
	
	@ApiModelProperty(example = "1", position = 3)
    private Long origemId;
	
	@ApiModelProperty(example = "2", position = 4)
    private Long destinoId;
	
	@ApiModelProperty(example = "100", position = 5)
    private BigDecimal valor;
}
