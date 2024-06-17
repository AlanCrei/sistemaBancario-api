package com.princeton.sistemaBancario.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Transferencia {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate data = LocalDate.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "origem_id")
    private Conta origem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destino_id")
    private Conta destino;

    private BigDecimal valor;
}