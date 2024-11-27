package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.dto.FuncionarioDTO;
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

    @GetMapping("/id/{id}")
    public String GetById(@RequestBody UUID id){
        return service.buscarPorId(id).getNome();
    }

    @GetMapping("/nome/{nome}")
    public String GetByName(@RequestBody FuncionarioDTO funcionarioDTO){
        List<Funcionario> lista = service.buscarPorNome(funcionarioDTO.getNome());
        for(Funcionario funcionario: lista){
            return funcionario.getNome();
        }
        return null;
    }

    @GetMapping("/lista")
    public List<String> AllFuncionarios(){
        return service.listarTodos();
    }

    @PostMapping("/registrar")
    public Funcionario criarFuncionario(@RequestBody @Valid FuncionarioDTO funcionarioDTO){
        var funcionario = service.criarFuncionario(funcionarioDTO);
        return funcionario;
    }

    @PutMapping("/atualizar")
    public Funcionario atualizarFuncionario(@RequestBody @Valid  FuncionarioDTO funcionarioDTO){
        var funcionario = service.atualizarFuncionario(funcionarioDTO.getId(), funcionarioDTO);
        return funcionario;
    }

    @DeleteMapping("/deletar-cpf")
    public Funcionario deletarFuncionario(@RequestBody @Valid  FuncionarioDTO funcionarioDTO){
        var funcionario = service.excluirFuncionario(funcionarioDTO.getCpf());
        return funcionario;
    }
}
