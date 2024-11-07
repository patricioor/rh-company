package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    List<Funcionario> GetFuncionariosByCargo(String cargo);
    List<Funcionario> GetFuncionariosBySetorId(UUID id);
    List<Funcionario> GetFuncionariosByStatus(Boolean status);

}
