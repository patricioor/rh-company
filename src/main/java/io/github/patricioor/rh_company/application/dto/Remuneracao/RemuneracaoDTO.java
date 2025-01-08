package io.github.patricioor.rh_company.application.dto.Remuneracao;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class RemuneracaoDTO {
    private UUID id;
    private BigDecimal salarioBase;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String tipoContrato;
    private Integer cargaHorariaSemanal;
    private BigDecimal adicionalPericulosidade;
    private BigDecimal adicionalInsalubridade;
    private BigDecimal bonus;

    private String funcionarioId;
}
