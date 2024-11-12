package io.github.patricioor.rh_company.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FuncionarioDTO {
    private String nome;
    private String cpf;
    private String cargo;
    private String setorId;
    private Date data;
    private Double salarioBase;
    private Boolean status;
}
