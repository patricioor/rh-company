package io.github.patricioor.rh_company.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "remuneracoes")
public class Remuneracao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "funcionarios_id", referencedColumnName = "id")
    private Funcionario funcionario;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "data")
    private Date data;
    @Column(name = "descricao")
    private String Descricao;
}
