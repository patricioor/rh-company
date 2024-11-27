package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.domain.Usuario;
import io.github.patricioor.rh_company.domain.dto.UsuarioDTO;
import io.github.patricioor.rh_company.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/usuario")
public class UsuarioController {
    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/lista")
    public List<Usuario> AllUsuarios(){
        return service.listarTodosOsUsuarios();
    }

    @GetMapping("/id")
    public Usuario GetUsuariByUsername(UsuarioDTO usuarioDTO){;
        return service.GetUsuarioByUsername(usuarioDTO.getUsername());
    }
}
