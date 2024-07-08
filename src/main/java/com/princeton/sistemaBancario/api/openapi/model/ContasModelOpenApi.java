package com.princeton.sistemaBancario.api.openapi.model;

import java.util.List;

import com.princeton.sistemaBancario.api.model.ContaModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("ContasModel")
@Setter
@Getter
public class ContasModelOpenApi {

	private List<ContaModel> content;
	
	@ApiModelProperty(example = "10", value = "Quantidade de registros por página", position = 1)
	private Long size;
	
	@ApiModelProperty(example = "50", value = "Total de registros", position = 2)
	private Long totalElements;
	
	@ApiModelProperty(example = "5", value = "Total de páginas", position = 3)
	private Long totalPages;
	
	@ApiModelProperty(example = "0", value = "Número da página (começa em 0)", position = 4)
	private Long number;	
}
