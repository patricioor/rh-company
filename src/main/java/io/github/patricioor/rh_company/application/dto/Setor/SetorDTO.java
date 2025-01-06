package io.github.patricioor.rh_company.application.dto.Setor;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioSetorDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetorDTO {
    private String id;
    private String nome;
    private List<FuncionarioSetorDTO> funcionarios;
}
