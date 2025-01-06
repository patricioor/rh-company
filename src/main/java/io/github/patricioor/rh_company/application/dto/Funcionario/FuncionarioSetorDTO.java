package io.github.patricioor.rh_company.application.dto.Funcionario;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FuncionarioSetorDTO {
    private String id;
    private String nome;
    private String cpf;
    private String cargo;
    private String genero;
    private LocalDate dataContratacao;
    private LocalDate dataNascimento;
    private Double salarioBase;
    private Boolean status;
}
