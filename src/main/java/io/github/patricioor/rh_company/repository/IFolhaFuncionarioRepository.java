package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.tabelas_relacionamentos.FolhaFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFolhaFuncionarioRepository extends JpaRepository<FolhaFuncionario, UUID> {
}
