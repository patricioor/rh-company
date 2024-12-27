package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    @Query("SELECT f FROM Funcionario f WHERE f.cargo = :cargo")
    List<Funcionario> findFuncionariosByCargo(@Param("cargo") String cargo);
    @Query("SELECT f FROM Funcionario f WHERE f.setor.id = :setorId")
    List<Funcionario> findFuncionariosBySetor(@Param("setorId") UUID setorId);
    @Query("SELECT f FROM Funcionario f WHERE f.status = :status")
    List<Funcionario> findFuncionariosByStatus(@Param("status")Boolean status);
    @Query("SELECT f FROM Funcionario f WHERE f.nome ILIKE :nome")
    Funcionario findFuncionarioByNome(@Param("nome") String nome);
    @Query("SELECT f FROM Funcionario f WHERE f.cpf = :cpf")
    Funcionario findFuncionarioByCpf(@Param("cpf") String cpf);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Funcionario (id, nome, cargo,setor_id, data_contratacao, salario_base, status, cpf, data_nascimento,genero) VALUES (:setorId, :funcionarioId)", nativeQuery = true)
    Funcionario updateFuncionarioByCpf()
}
