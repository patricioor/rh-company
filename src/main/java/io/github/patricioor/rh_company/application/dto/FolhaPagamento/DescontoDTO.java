package io.github.patricioor.rh_company.application.dto.FolhaPagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DescontoDTO {
    private String id;
    private String descricao;
    private Double valor;

    private String folhaPagamentoId;
}
