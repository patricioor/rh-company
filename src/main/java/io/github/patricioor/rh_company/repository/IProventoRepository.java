package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.FolhaPagamento.Provento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProventoRepository extends JpaRepository<Provento, UUID> {
}
