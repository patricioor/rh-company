package io.github.patricioor.rh_company.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "remuneracoes")
public class Remuneracao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "salario_base")
    private BigDecimal salarioBase;
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    @Column(name = "data_fim")
    private LocalDate dataFim;
    @NotBlank
    @Column(name = "tipo_contrato")
    private String tipoContrato;
    @Column(name = "carga_horaria_semanal")
    private Integer cargaHorariaSemanal;
    @Column(name = "adicional_periculosidade")
    private BigDecimal adicionalPericulosidade;
    @Column(name = "adicional_insalubridade")
    private BigDecimal adicionalInsalubridade;
    @Column(name = "bonus")
    private BigDecimal bonus;

    @JoinColumn(name = "funcionarios_id", referencedColumnName = "id")
    private UUID funcionarioId;

    public BigDecimal calcularTotalRemuneracao(){
        return salarioBase
                .add(adicionalPericulosidade != null ? adicionalPericulosidade: BigDecimal.ZERO)
                .add(adicionalInsalubridade != null ? adicionalInsalubridade: BigDecimal.ZERO)
                .add(bonus != null ? bonus : BigDecimal.ZERO);
    }
}
