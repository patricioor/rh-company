package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Remuneracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IRemuneracaoRepository extends JpaRepository<Remuneracao, UUID> {
    @Query("SELECT r FROM Remuneracao r WHERE r.funcionario.id = :funcionarioId")
    List<Remuneracao> findByFuncionarioId(@Param("funcionarioId") UUID funcionarioId);

    @Query("SELECT r FROM Remuneracao r WHERE r.tipo = :tipo")
    List<Remuneracao> findByTipo(String tipo);
}
