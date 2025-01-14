package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.FolhaPagamento.FolhaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFolhaPagamentoRepository extends JpaRepository<FolhaPagamento, UUID> {
    @Query("SELECT fp FROM FolhaPagamento fp WHERE fp.funcionarioId = :funcionarioId")
    List<FolhaPagamento> findFolhaPagamentoByFuncionarioId(@Param("funcionarioId") UUID funcionarioId);
}
