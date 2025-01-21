package io.github.patricioor.rh_company.domain.FolhaPagamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "descontos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Desconto {
    @Id
    private UUID id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "valor")
    private Double valor;

    private String folhaPagamentoId;
}
