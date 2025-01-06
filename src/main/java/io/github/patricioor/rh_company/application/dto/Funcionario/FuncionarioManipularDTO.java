package io.github.patricioor.rh_company.application.dto.Funcionario;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class FuncionarioManipularDTO {
    private String nome;
    private String cargo;
    private String genero;
    private LocalDate dataContratacao;
    private LocalDate dataNascimento;
    private Double salarioBase;
    private Boolean status;
}
