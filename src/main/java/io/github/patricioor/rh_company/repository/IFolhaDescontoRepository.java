package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.tabelas_relacionamentos.FolhaDesconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFolhaDescontoRepository extends JpaRepository<FolhaDesconto, UUID> {
    @Query(value = "SELECT desconto_id FROM folha_desconto WHERE folha_pagamento_id = :folhaPagamentoId",nativeQuery = true)
    List<UUID> listarDescontoPorFolhaPagamentoId(@Param("folhaPagamentoId") UUID folhaPagamentoId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO folha_desconto VALUES (:folhaPagamentoId, :descontoId)", nativeQuery = true)
    void inserirDescontoNaFolha(@Param("folhaPagamentoId") UUID folhaPagamentoId, @Param("descontoId") UUID descontoId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM folha_desconto WHERE folha_desconto.desconto_id = :descontoId" ,nativeQuery = true)
    void excluirDescontoByFolhaPagamentoId(@Param("descontoId") UUID descontoId);
}
