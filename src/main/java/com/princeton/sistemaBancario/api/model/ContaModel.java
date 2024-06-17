package com.princeton.sistemaBancario.api.model;

import java.math.BigDecimal;

import com.princeton.sistemaBancario.domain.model.Banco;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContaModel {
	
    private Long id;

    private Banco banco;

    private String conta;

    private String beneficiario;

    private BigDecimal saldo;
}
