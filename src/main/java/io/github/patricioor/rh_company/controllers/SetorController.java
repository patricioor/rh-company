package io.github.patricioor.rh_company.controllers;

import io.github.patricioor.rh_company.domain.Setor;
import io.github.patricioor.rh_company.application.dto.SetorDTO;
import io.github.patricioor.rh_company.service.SetorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/registrar")
    public Setor criarSetor(@RequestBody @Valid SetorDTO setorDTO){
        return service.criarSetor(setorDTO.getNome());
    }

    @PutMapping("/atualizar")
    public void atualizarSetor(@RequestBody @Valid SetorDTO setorDTO){
        service.AtualizarSetor(setorDTO);
    }

    @DeleteMapping("deletar-id")
    public void deletarSetor(@RequestParam("setorId") @Valid String id ){
        service.ApagarSetorPeloId(id);
    }
}
