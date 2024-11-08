package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    @Query("SELECT f FROM Funcionario f WHERE f.cargo = :cargo")
    List<Funcionario> findFuncionariosByCargo(String cargo);
    @Query("SELECT f FROM Funcionario f WHERE f.setorId.id = :setorId")
    List<Funcionario> findFuncionariosBySetorId(@Param("setorId") UUID setorId);
    @Query("SELECT f FROM Funcionario f WHERE f.status = :status")
    List<Funcionario> findFuncionariosByStatus(Boolean status);

    @Query("SELECT f FROM Funcionario f WHERE f.nome ILIKE %:nome%")
    List<Funcionario> findFuncionarioByNome(@Param("nome") String nome);
}
