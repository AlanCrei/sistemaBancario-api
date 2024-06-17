package com.princeton.sistemaBancario.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferenciaModel {
	
	private UUID id;
    private LocalDate data;
    private Long origemId;
    private Long destinoId;
    private BigDecimal valor;
}
