package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Usuario;
import io.github.patricioor.rh_company.application.dto.UsuarioDTO;
import io.github.patricioor.rh_company.domain.exception.ElementAlreadyExistsException;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private IUsuarioRepository repository;

    public List<Usuario> listarTodosOsUsuarios() {
        return repository.findAll();
    }

    public Usuario buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Usuário"));
    }

    Boolean UsuarioExiste(Usuario usuario) {
        return repository.existsById(usuario.getId());
    }

    public Usuario criarUsuario(UsuarioDTO usuariodto) {
        if (repository.findUsurioByUsername(usuariodto.getUsername()) != null) {
            throw new ElementAlreadyExistsException("Usuário");
        }

        Usuario usuario = new Usuario();
        usuario.setUsuario(usuariodto.getUsername());
        usuario.setSenha(usuariodto.getSenha());
        usuario.setPerfil(usuariodto.getPerfil());

        repository.save(usuario);
        return usuario;
    }

    public Usuario GetUsuarioByUsername(String usuario){
        try{
            var usuarioFound = repository.findUsurioByUsername(usuario);
            return usuarioFound;
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException("Usuário");
        }
    }

    public Usuario atualizarUsername(UsuarioDTO usuariodto) {
        var usuario = GetUsuarioByUsername(usuariodto.getUsername());

        if (usuario == null) {
            throw new ElementNotFoundException("Usuário");
        }

        usuario.setUsuario(usuariodto.getUsername());

        repository.save(usuario);
        return usuario;
    }

    public Usuario atualizarSenha(UsuarioDTO usuariodto) {
        var usuario = repository.findUsurioByUsername(usuariodto.getUsername());

        if (usuario == null) {
            throw new ElementNotFoundException("Usuário");
        }

        usuario.setSenha(usuariodto.getSenha());

        repository.save(usuario);
        return usuario;
    }

    public Usuario atualizarPerfil(UsuarioDTO usuariodto) {
        var usuario = repository.findUsurioByUsername(usuariodto.getUsername());

        if (usuario == null) {
            throw new ElementNotFoundException("Usuário");
        }

        usuario.setPerfil(usuariodto.getPerfil());

        repository.save(usuario);
        return usuario;
    }

    public Usuario apagarUsuario(UsuarioDTO usuarioDTO) {
        try{
            var usuario = repository.findUsurioByUsername(usuarioDTO.getUsername());
            repository.deleteById(usuario.getId());
            return usuario;
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Usuário");
        }
    }
}

