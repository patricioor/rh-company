package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioManipularDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioDTO;
import io.github.patricioor.rh_company.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService service;

    @Autowired
    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping("/id/{id}")
    public FuncionarioDTO GetById(@RequestParam("id") @Valid String id){
        return service.buscarPorId(id);
    }

    @GetMapping("/nome/{nome}")
    public FuncionarioDTO GetByName(@RequestParam("nome") @Valid String nome){
        return service.buscarPorNome(nome);
    }

    @GetMapping("/lista")
    public List<FuncionarioDTO> AllFuncionarios(){
        return service.listarTodos();
    }

    @PostMapping("/registrar")
    public FuncionarioDTO criarFuncionario(@RequestParam("cpf") @Valid String cpf, @RequestBody @Valid FuncionarioManipularDTO funcionarioManipularDTO){
        return service.criarFuncionario(cpf, funcionarioManipularDTO);
    }

    @PutMapping("/atualizar")
    public FuncionarioDTO atualizarFuncionario(@RequestParam("cpf") @Valid String cpf, @RequestBody @Valid FuncionarioManipularDTO funcionarioManipularDTO){
        return service.atualizarFuncionario(cpf, funcionarioManipularDTO);
    }

    @PutMapping("/alterar-setor-funcionario")
    public FuncionarioDTO alterarSetorFuncionario(@RequestParam("cpf") @Valid String cpf, @RequestParam("nomeSetor") @Valid String nomeSetor){
        return service.alterarSetorFuncionar(cpf, nomeSetor);
    }

    @DeleteMapping("/deletar-cpf")
    public FuncionarioDTO deletarFuncionario(@RequestParam("cpf") @Valid String cpf){
        return service.excluirFuncionario(cpf);
    }
}
