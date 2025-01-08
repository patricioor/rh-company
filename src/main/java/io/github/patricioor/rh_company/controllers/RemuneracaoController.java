package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.application.dto.Remuneracao.RemuneracaoDTO;
import io.github.patricioor.rh_company.application.dto.Remuneracao.RemuneracaoManipularDTO;
import io.github.patricioor.rh_company.service.RemuneracaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/remuneracao")
public class RemuneracaoController {

    private final RemuneracaoService service;

    @Autowired
    public RemuneracaoController(RemuneracaoService service) {
        this.service = service;
    }

    @GetMapping("/id/{id}")
    public RemuneracaoDTO GetById(@RequestParam("id") @Valid String id){
        return service.buscarPorId(id);
    }

    @PostMapping("/registrar")
    public RemuneracaoDTO criarRemuneracao(@RequestBody @Valid RemuneracaoManipularDTO remuneracaoManipularDTO){
        return service.criarRemuneracao(remuneracaoManipularDTO);
    }

}
