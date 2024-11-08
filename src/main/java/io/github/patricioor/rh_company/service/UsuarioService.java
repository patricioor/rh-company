package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Usuario;
import io.github.patricioor.rh_company.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private IUsuarioRepository repository;

    List<Usuario> listarTodosOsUsuarios(){
        return repository.findAll();
    }

    Optional<Usuario> retornarUsuarioPeloId(UUID id){
        return repository.findById(id);
    }

    Boolean UsuarioExiste(String usuario){
        return repository.existByUsuario(usuario);
    }

    void criarUsuario(Usuario usuario){
        repository.save(usuario);
    }

    void alterarUsuario(UUID id, Usuario usuario){
        repository.UpdateUsuario(id, usuario);
    }

    void apagarUsuario(UUID id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
