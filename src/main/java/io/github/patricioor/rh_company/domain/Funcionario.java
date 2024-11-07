package io.github.patricioor.rh_company.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    @Column(name = "nome")
    private String nome;
    @Column(name = "cargo")
    private String cargo;
    @ManyToOne
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    private Setor setorId;
    @Column(name = "data_contratacao")
    private Date data;
    @Column(name = "salario_base")
    private Double salarioBase;
    @Column(name = "status")
    private Boolean status;
}
