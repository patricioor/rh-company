package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.application.dto.Remuneracao.RemuneracaoDTO;
import io.github.patricioor.rh_company.application.dto.Remuneracao.RemuneracaoManipularDTO;
import io.github.patricioor.rh_company.service.RemuneracaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/remuneracao")
@RequestMapping("/remuneracao")
public class RemuneracaoController {

    private final RemuneracaoService service;

    @Autowired
    public RemuneracaoController(RemuneracaoService service) {
        this.service = service;
    }

    @GetMapping("/lista/{funcionarioId}")
    public List<RemuneracaoDTO> AllRemuneracoesByFuncionarioId(@RequestParam("funcionarioId") @Valid String funcionarioId){
        return service.AllRemuneracoesByFuncionarioId(funcionarioId);
    }

    @GetMapping("/id")
    public RemuneracaoDTO GetById(@RequestParam("id") @Valid String id){
        return service.buscarPorId(id);
    }

    @PostMapping("/registrar")
    public RemuneracaoDTO criarRemuneracao(@RequestBody @Valid RemuneracaoManipularDTO remuneracaoManipularDTO){
        return service.criarRemuneracao(remuneracaoManipularDTO);
    }

    @PutMapping("/atualizar")
    public RemuneracaoDTO atualizarRemuneracao(@RequestParam("id") @Valid String id, @RequestBody RemuneracaoManipularDTO remuneracaoManipularDTO){
        return service.alterarRemuneracaoPeloId(id, remuneracaoManipularDTO);
    }

    @DeleteMapping("/excluir")
    public void excluirRemuneracao(@RequestParam("id") @Valid String id){
        service.excluirRemuneracao(UUID.fromString(id));
    }

}
