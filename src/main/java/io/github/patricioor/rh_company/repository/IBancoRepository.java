package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IBancoRepository extends JpaRepository<Banco, UUID> {
}
