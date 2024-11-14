package io.github.patricioor.rh_company.domain.dto;

import io.github.patricioor.rh_company.domain.Funcionario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BancoDTO {
    private String id;
    private Funcionario funcionario;
    private String nomeBanco;
    private String agencia;
    private String conta;
}
