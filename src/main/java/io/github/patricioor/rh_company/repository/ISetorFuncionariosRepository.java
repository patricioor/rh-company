package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.SetorFuncionarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ISetorFuncionariosRepository extends JpaRepository<SetorFuncionarios, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO setor_funcionarios (setor_id, funcionario_id) VALUES (:setorId, :funcionarioId)", nativeQuery = true)
    void inserirFuncionarioNoSetor(@Param("setorId") UUID setorId, @Param("funcionarioId") UUID funcionarioId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM setor_funcionarios WHERE setor_funcionarios.funcionario_id = (:funcionarioId)", nativeQuery = true)
    void deleteSetorFuncionariosById(@Param("funcionarioId") UUID funcionarioId);
}
