package io.github.patricioor.rh_company.domain.tabelas_relacionamentos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Entity
@Table(name = "folha_remuneracao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolhaRemuneracao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "funcionario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UUID funcionarioId;
    @Column(name = "remuneracao_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UUID remuneracaoId;
}
