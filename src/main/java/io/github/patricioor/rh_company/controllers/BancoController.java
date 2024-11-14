package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.domain.Banco;
import io.github.patricioor.rh_company.domain.dto.BancoDTO;
import io.github.patricioor.rh_company.service.BancoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/banco")
public class BancoController {
    private final BancoService service;

    @Autowired
    public BancoController(BancoService service) {
        this.service = service;
    }

    @PostMapping("/registrar")
    public Banco criarBanco(@RequestBody @Valid BancoDTO bancoDTO){
        return service.criarBanco(bancoDTO);
    }

    @PutMapping("/atualizar")
    public Banco atualizar(@RequestBody @Valid BancoDTO bancoDTO){
        return service.atualizarBanco(bancoDTO);
    }

    @DeleteMapping("deletar-id")
    public Banco deletarBanco(@RequestBody @Valid BancoDTO bancoDTO){
        return service.deletarBanco(bancoDTO);
    }

}
