package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioManipularDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioSetorDTO;
import io.github.patricioor.rh_company.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService service;

    @Autowired
    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping("/id")
    public FuncionarioDTO GetById(@RequestParam("id") @Valid String id){
        return service.buscarPorId(id);
    }

    @GetMapping("/nome")
    public FuncionarioDTO GetByName(@RequestParam("nome") @Valid String nome){
        return service.buscarPorNome(nome);
    }

    @GetMapping("/status")
    public List<FuncionarioDTO> GetByStatus(@RequestParam("status") @Valid Boolean bool){
        return service.retornarListaFuncionariosPeloStatus(bool);
    }

    @GetMapping("/listar-setor_Id")
    public List<FuncionarioSetorDTO> GetBySetorId(@RequestParam("setorId") @Valid String id){
        return service.retornarListaFuncionariosPeloSetorId(UUID.fromString(id));
    }

    @GetMapping("/listar-todos")
    public List<FuncionarioDTO> AllFuncionarios(){
        return service.listarTodos();
    }

    @GetMapping("/listar-cargo")
    public List<FuncionarioDTO> GetByCargo(@RequestParam("cargo") @Valid String cargo){
        return service.retornarListaFuncionariosPeloCargo(cargo);
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

    @PutMapping("/remover-funcionario-do-setor")
    public void removerFuncionarioDoSetor(@RequestParam("id") @Valid String id){
        service.excluirFuncionarioDoSetor(UUID.fromString(id));
    }

    @DeleteMapping("/excluir")
    public FuncionarioDTO deletarFuncionario(@RequestParam("cpf") @Valid String cpf){
        return service.excluirFuncionario(cpf);
    }
}
