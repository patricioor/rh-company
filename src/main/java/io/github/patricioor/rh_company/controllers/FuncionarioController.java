package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioAtualizarDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioDTO;
import io.github.patricioor.rh_company.domain.Funcionario;
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
    public Funcionario GetById(@RequestParam("id") String id){
        return service.buscarPorId(id);
    }

    @GetMapping("/nome/{nome}")
    public Funcionario GetByName(@RequestParam("nome") @Valid String nome){
        return service.buscarPorNome(nome);
    }

    @GetMapping("/lista")
    public List<String> AllFuncionarios(){
        return   service.listarTodos();
    }

    @PostMapping("/registrar")
    public Funcionario criarFuncionario(@RequestParam("cpf") @Valid String cpf, @RequestBody @Valid FuncionarioAtualizarDTO funcionarioAtualizarDTO){
        return service.criarFuncionario(funcionarioAtualizarDTO, cpf);
    }

    @PutMapping("/atualizar")
    public Funcionario atualizarFuncionario(@RequestParam("cpf") @Valid String cpf, @RequestBody @Valid FuncionarioAtualizarDTO funcionarioAtualizarDTO){
        return service.atualizarFuncionario(cpf, funcionarioAtualizarDTO);
    }

    @PutMapping("/alterar-setor-funcionario")
    public Funcionario alterarSetorFuncionario(@RequestParam("cpf") @Valid String cpf, @RequestParam("nomeSetor") @Valid String nomeSetor){
        return service.alterarSetorFuncionar(cpf, nomeSetor);
    }

    @DeleteMapping("/deletar-cpf")
    public Funcionario deletarFuncionario(@RequestBody @Valid  FuncionarioDTO funcionarioDTO){
        return service.excluirFuncionario(funcionarioDTO.getCpf());
    }
}
