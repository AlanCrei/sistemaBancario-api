package com.princeton.sistemaBancario.api.openapi.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.princeton.sistemaBancario.api.exceptionhandler.Problem;
import com.princeton.sistemaBancario.api.model.TransferenciaModel;
import com.princeton.sistemaBancario.api.model.input.TransferenciaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Transferencias")
public interface TransferenciaControllerOpenApi {

	@ApiOperation("Realiza uma transferência")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Conta não encontrada", response = Problem.class)
	})
	TransferenciaModel criarTransferencia(
			@ApiParam(name = "corpo", value = "Representação de uma transferência", required = true)
			TransferenciaInput transferenciaInput);
	
	
	@ApiOperation("Lista as transferências")
	public Page<TransferenciaModel> listarTransferencias(
             Long idContaOrigem,
             Long idContaDestino,
             LocalDate dataInicial,
             LocalDate dataFinal,
             int page,
             int size) ;
}
