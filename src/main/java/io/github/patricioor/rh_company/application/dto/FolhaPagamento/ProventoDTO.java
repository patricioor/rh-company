package io.github.patricioor.rh_company.application.dto.FolhaPagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProventoDTO {
    private String Id;
    private String descricao;
    private Double valor;

    private String folhaPagamentoId;
}
