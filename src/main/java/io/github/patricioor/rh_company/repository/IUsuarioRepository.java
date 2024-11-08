package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByUsuario(String usuario);
    boolean existByUsuario(String usuario);
    void UpdateUsuario(UUID id, Usuario usuario);
}
