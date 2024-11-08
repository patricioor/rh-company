package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISetorRepository extends JpaRepository<Setor, UUID> {
    @Query("SELECT s FROM Setor s WHERE s.nome = :nome")
    Setor findSetorByName(String nome);
}
