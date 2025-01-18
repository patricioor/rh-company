package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoManipularDTO;
import io.github.patricioor.rh_company.service.FolhaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folha-pagamento")

public class FolhaPagamentoController {
    private final FolhaPagamentoService service;
    @Autowired
    public FolhaPagamentoController(FolhaPagamentoService service) {
        this.service = service;
    }

    @GetMapping("/id")
    public FolhaPagamentoDTO GetById(@RequestParam("id") @Valid String id){
       return service.BuscarPorId(id);
    }

    @PostMapping("/registrar")
    public FolhaPagamentoDTO criarFolhaPagamento(@RequestBody @Valid FolhaPagamentoManipularDTO folhaPagamentoManipularDTO){
        return service.criarFolhaPagamento(folhaPagamentoManipularDTO);
    }
}