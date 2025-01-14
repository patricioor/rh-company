package io.github.patricioor.rh_company.application.dto.FolhaPagamento;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FolhaPagamentoManipularDTO {
    private LocalDate dataEmissao;
    private String periodoReferencia;
    private BigDecimal salarioBruto;
    private String funcionarioId;

    List<DescontoManipularDTO> descontos;
    List<ProventoManipularDTO> proventos;
}
