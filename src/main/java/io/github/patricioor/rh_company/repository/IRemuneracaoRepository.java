package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Remuneracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IRemuneracaoRepository extends JpaRepository<Remuneracao, UUID> {
    List<Remuneracao> findByFuncionarioId(UUID id);
    List<Remuneracao> findByTipo(String tipo);
    void UpdateRemuneracao(UUID id, Remuneracao remuneracao);

}
