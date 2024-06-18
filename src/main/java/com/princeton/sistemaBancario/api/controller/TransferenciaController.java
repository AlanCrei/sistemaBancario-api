package com.princeton.sistemaBancario.api.controller;


import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.princeton.sistemaBancario.api.model.TransferenciaModel;
import com.princeton.sistemaBancario.api.model.input.TransferenciaInput;
import com.princeton.sistemaBancario.domain.service.TransferenciaService;

@RestController
@RequestMapping(value = "/transferencias")
public class TransferenciaController {
	
	@Autowired
	TransferenciaService transferenciaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyAuthority('ROLE_TRANSF_ADD')")
	public TransferenciaModel criarTransferencia(@RequestBody @Valid  TransferenciaInput transferenciaInput) {
		
		return transferenciaService.criarTransferencia(transferenciaInput);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ROLE_TRANSF_LST')")
    public Page<TransferenciaModel> listarTransferencias(
            @RequestParam(required = false) Long idContaOrigem,
            @RequestParam(required = false) Long idContaDestino,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return transferenciaService.listarTransferencias(idContaOrigem, idContaDestino, dataInicial, dataFinal, pageable);
    }
}
