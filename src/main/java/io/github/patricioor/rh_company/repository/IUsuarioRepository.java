package io.github.patricioor.rh_company.repository;

import io.github.patricioor.rh_company.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, UUID> {

    @Query("SELECT u FROM Usuario u WHERE u.usuario = :usuario")
    Usuario findUsurioByUsername(String usuario);
}
