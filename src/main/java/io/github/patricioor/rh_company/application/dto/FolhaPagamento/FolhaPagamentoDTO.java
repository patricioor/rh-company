package io.github.patricioor.rh_company.application.dto.FolhaPagamento;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class FolhaPagamentoDTO {
    private UUID id;
    private LocalDate dataEmissao;
    private String periodoReferencia;
    private Double salarioBruto;
    private Double salarioLiquido;
    private String funcionarioId;

    List<DescontoDTO> descontos;
    List<ProventoDTO> proventos;
}
