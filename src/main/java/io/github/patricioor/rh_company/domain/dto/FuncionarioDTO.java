package io.github.patricioor.rh_company.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FuncionarioDTO {
    private String id;
    private String nome;
    private String cpf;
    private String cargo;
    private String genero;
    private String email;
    private LocalDate dataContratacao;
    private LocalDate dataNascimento;
    private Double salarioBase;
    private Boolean status;

    private String banco;
    private String setor;
}
