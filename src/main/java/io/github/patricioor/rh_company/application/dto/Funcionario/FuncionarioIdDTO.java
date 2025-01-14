package io.github.patricioor.rh_company.application.dto.Funcionario;

import io.github.patricioor.rh_company.application.dto.Setor.SetorDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FuncionarioIdDTO {
    private String id;
    private String nome;
    private String cpf;
    private String cargo;
    private String genero;
    private LocalDate dataContratacao;
    private LocalDate dataNascimento;
    private Boolean status;
    private SetorDTO setor;
}
