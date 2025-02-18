package io.github.patricioor.rh_company.domain;

import io.github.patricioor.rh_company.domain.FolhaPagamento.FolhaPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Column(name = "nome")
    private String nome;
    @NotBlank
    @Column(name = "cpf")
    private String cpf;
    @NotBlank
    @Column(name = "cargo")
    private String cargo;
    @NotBlank
    @Column(name = "genero")
    private String genero;
    @NotNull
    @Column(name = "data_contratacao")
    private LocalDate dataContratacao;
    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @NotNull
    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<FolhaPagamento> folhaPagamentos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    private Setor setor;
}