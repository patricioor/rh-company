package io.github.patricioor.rh_company.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "banco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Banco {
    @Id
    private UUID id;

    @NotNull
    @OneToOne
    @MapsId
    @JoinColumn(name = "funcinario_id")
    private Funcionario funcionario;

    @NotBlank
    @Column(name = "banco")
    private String banco;
    @NotBlank
    @Column(name = "agencia")
    private String agencia;
    @NotBlank
    @Column(name = "conta")
    private String conta;



}
