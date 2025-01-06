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
    @Query("SELECT s FROM Setor s WHERE s.nome = :nome")
    Setor findSetorByName(String nome);

    @Modifying
    @Transactional
    @Query (value = "UPDATE Setor s SET nome = :#{#nomeSetorNovo} WHERE s.nome = :#{#nomeSetorAntigo}", nativeQuery = true)
    void updateSetorByName(@Param("nomeSetorAntigo") String nomeSetorAntigo, @Param("nomeSetorNovo") String nomeSetorNovo);
}
