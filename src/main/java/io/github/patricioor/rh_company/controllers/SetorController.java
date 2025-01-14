package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.application.dto.Setor.SetorDTO;
import io.github.patricioor.rh_company.service.SetorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setor")
public class SetorController {
    private final SetorService service;

    @Autowired
    public SetorController(SetorService service) {
        this.service = service;
    }

    @GetMapping("/lista")
    public List<String> AllSetores(){
        return service.listaSetoresNome();
    }

    @GetMapping("/id")
    public SetorDTO BuscarSetorId(@RequestParam("id") @Valid String id){
        return service.buscarPorId(UUID.fromString(id));
    }    @GetMapping("/nome")
    public SetorDTO BuscarSetorNome(@RequestParam("nome") @Valid String nome){
        return service.buscarSetorPeloNome(nome);
    }

    @PostMapping("/registrar")
    public SetorDTO criarSetor(@RequestBody @Valid String nome){
        return service.criarSetor(nome);
    }

    @PutMapping("/atualizar")
    public void atualizarSetor(@RequestParam("nomeSetorAntigo") @Valid String nomeSetorAntigo, @RequestParam("nomeSetorNovo") @Valid String nomeSetorNovo){
        service.atualizarSetor(nomeSetorAntigo, nomeSetorNovo);
    }

    @DeleteMapping("deletar")
    public void deletarSetor(@RequestParam("setorId") @Valid String id ){
        service.apagarSetorPeloId(id);
    }
}
