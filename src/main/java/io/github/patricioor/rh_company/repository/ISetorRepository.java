package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ISetorRepository extends JpaRepository<Setor, UUID> {

    @Query(value = "SELECT * FROM setor AS s WHERE s.nome ILIKE CONCAT('%',:nome,'%')", nativeQuery = true)
    Setor findByNome(@Param("nome") String nome);

    @Modifying
    @Transactional
    @Query ("UPDATE Setor s SET s.nome = :nomeSetorNovo WHERE s.nome = :nomeSetorAntigo")
    void updateSetorByName(@Param("nomeSetorAntigo") String nomeSetorAntigo, @Param("nomeSetorNovo") String nomeSetorNovo);
}
