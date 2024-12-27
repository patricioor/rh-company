package io.github.patricioor.rh_company.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table( name = "setor_funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetorFuncionarios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "setor_id", nullable = false)
    private UUID setorId;

    @Column(name = "funcionario_id", nullable = false)
    private UUID funcionarioId;
}
