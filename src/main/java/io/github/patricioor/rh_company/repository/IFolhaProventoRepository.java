package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.FolhaPagamento.Provento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFolhaProventoRepository extends JpaRepository<Provento, UUID> {
    @Query(value = "SELECT provento_id FROM folha_provento WHERE folha_pagamento_id = :folhaPagamentoId", nativeQuery = true)
    List<UUID> listarProventoPorFolhaPagamentoId(@Param("folhaPagamentoId") UUID folhaPagamentoId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO folha_provento VALUES (:folhaPagamentoId, :proventoId)", nativeQuery = true)
    void inserirProventoNaFolha(@Param("folhaPagamentoId") UUID folhaPagamentoId, @Param("proventoId") UUID proventoId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM folha_provento WHERE folha_provento.provento_id = (:proventoId)", nativeQuery = true)
    void deleteProventoByFolhaPagamentoId(@Param("proventoId") UUID proventoId);
}
