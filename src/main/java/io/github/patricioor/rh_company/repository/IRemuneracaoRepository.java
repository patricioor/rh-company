package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Remuneracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface IRemuneracaoRepository extends JpaRepository<Remuneracao, UUID> {
    @Query("SELECT r FROM Remuneracao r WHERE r.funcionarioId = :funcionarioId")
    List<Remuneracao> findListaRemuneracaoByFuncionarioId(@Param("funcionarioId") UUID funcionarioId);
    @Query("SELECT r FROM Remuneracao r WHERE r.tipoContrato = :tipoContrato AND r.dataInicio = :data")
    List<Remuneracao> findListaRemuneracaoByTipoContrato(@Param("tipoContrato") String tipoContrato, @Param("data")LocalDate data);

    @Modifying
    @Transactional
    @Query("UPDATE Remuneracao r " +
            "SET r.salarioBase = :#{#remuneracao.salarioBase}," +
            "r.dataInicio = :#{#remuneracao.dataInicio}," +
            "r.dataFim = :#{#remuneracao.dataFim}," +
            "r.tipoContrato = :#{#remuneracao.tipoContrato}," +
            "r.cargaHorariaSemanal = :#{#remuneracao.cargaHorariaSemanal}," +
            "r.adicionalPericulosidade = :#{#remuneracao.adicionalPericulosidade}," +
            "r.adicionalInsalubridade = :#{#remuneracao.adicionalInsalubridade}," +
            "r.bonus = :#{#remuneracao.bonus} " +
            "WHERE r.funcionarioId = :#{#remuneracao.funcionarioId}"
    )
    void updateRemuneracao(@Param("remuneracao") Remuneracao remuneracao);
}

