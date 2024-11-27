package io.github.patricioor.rh_company.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    @Column(name = "tipo")
    private String tipo;
    @NotBlank
    @Column(name = "data")
    private Date data;
    @NotBlank
    @Column(name = "descricao")
    private String Descricao;
}
