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
@Table(name = "folha_provento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolhaProvento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "folha_pagamento_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UUID folhaPagamentoId;

    @Column(name = "provento_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UUID proventoId;
}
