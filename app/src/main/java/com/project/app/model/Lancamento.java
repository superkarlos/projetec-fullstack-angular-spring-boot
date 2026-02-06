package com.project.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.project.app.enumerador.TipoLancamento;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Entity
@Table(name = "lancamento")
@Data
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    
    @NotNull
    private String descricao;
    @NotNull
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
    @NotNull
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private String observacao;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}