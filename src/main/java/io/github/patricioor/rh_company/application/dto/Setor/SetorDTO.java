package io.github.patricioor.rh_company.application.dto.Setor;

import io.github.patricioor.rh_company.domain.Funcionario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetorDTO {
    private String id;
    private String nome;
    private List<Funcionario> funcionarios;
}
