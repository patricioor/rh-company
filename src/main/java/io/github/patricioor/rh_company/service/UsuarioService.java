package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Setor;
import io.github.patricioor.rh_company.domain.Usuario;
import io.github.patricioor.rh_company.exception.ElementAlreadyExistsException;
import io.github.patricioor.rh_company.exception.ElementNotFoundException;
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

    public Usuario buscarPorId (UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Usuário"));
    }

    Boolean UsuarioExiste(Usuario usuario){
        return repository.existsById(usuario.getId());
    }

    void criarUsuario(Usuario usuario){
        if(repository.existsById(usuario.getId())) {
            repository.save(usuario);
        } else {
            throw new ElementAlreadyExistsException("Usuário");
        }
    }

    void alterarPerfil(UUID id, String perfil){
        var usuario = buscarPorId(id);
        usuario.setPerfil(perfil);
        repository.save(usuario);
    }

    void alterarUsuario(UUID id, String username){
        var usuario = buscarPorId(id);
        usuario.setPerfil(username);
        repository.save(usuario);
    }

    void alterarSenha(UUID id, String senha){
        var usuario = buscarPorId(id);
        usuario.setPerfil(senha);
        repository.save(usuario);
    }

    void apagarUsuario(UUID id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
