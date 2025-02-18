package io.github.patricioor.rh_company.domain.FolhaPagamento;

import io.github.patricioor.rh_company.domain.Funcionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "folha_pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolhaPagamento {
    @Id
    private UUID id;
    @Column(name = "data_emissao")
    private LocalDate dataEmissao;
    @Column(name = "periodo_referencia")
    private String periodoReferencia;
    @Column(name = "salario_bruto")
    private Double salarioBruto;
    @Column(name = "salario_liquido")
    private Double salarioLiquido;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @OneToMany
    List<Desconto> descontos;

    @OneToMany
    List<Provento> proventos;
}
