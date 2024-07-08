package com.princeton.sistemaBancario.api.openapi.controller;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.princeton.sistemaBancario.api.exceptionhandler.Problem;
import com.princeton.sistemaBancario.api.model.ContaModel;
import com.princeton.sistemaBancario.api.model.input.ContaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Contas")
public interface ContaControllerOpenApi {

	@ApiOperation("Lista as contas")
	Page<ContaModel> listar(Pageable pageable);
	
	@ApiOperation("Cadastra uma conta")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Entidade não encontrada", response = Problem.class)
	})
	ContaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova conta", required = true)
					ContaInput contaInput);

	@ApiOperation("Atualiza uma conta por ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Conta atualizada"),
			@ApiResponse(code = 404, message = "Conta não encontrada", response = Problem.class)
	})
	ContaModel atualizar(
			@ApiParam(value = "ID de uma conta", example = "1", required = true)
					Long id,

			@ApiParam(name = "corpo", value = "Representação de uma conta com os novos dados", required = true)
				ContaInput contaInput);

	@ApiOperation("Exclui uma conta por ID")
	@ApiResponses({
			@ApiResponse(code = 204, message = "Conta excluída"),
			@ApiResponse(code = 404, message = "Conta não encontrada", response = Problem.class),
			@ApiResponse(code = 409, message = "Conflict", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma conta", example = "1", required = true)
					Long contaId);

}
