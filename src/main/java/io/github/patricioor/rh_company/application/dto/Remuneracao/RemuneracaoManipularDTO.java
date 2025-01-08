package io.github.patricioor.rh_company.application.dto.Remuneracao;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RemuneracaoManipularDTO {
    private BigDecimal salarioBase;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String tipoContrato;
    private int cargaHorariaSemanal;
    private BigDecimal adicionalPericulosidade;
    private BigDecimal adicionalInsalubridade;
    private BigDecimal bonus;
    private String funcionarioId;
}
