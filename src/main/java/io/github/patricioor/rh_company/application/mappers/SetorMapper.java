package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioSetorDTO;
import io.github.patricioor.rh_company.application.dto.Setor.SetorDTO;
import io.github.patricioor.rh_company.application.dto.Setor.SetorManipularDTO;
import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.Setor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SetorMapper {
    public Setor toSetor(SetorDTO setorDTO){
        var setor = new Setor();
        List<Funcionario> list = new ArrayList<>();
        setor.setId(UUID.fromString(setorDTO.getId()));
        setor.setNome(setorDTO.getNome());

        if(setorDTO.getFuncionarios() != null) {
            for (FuncionarioSetorDTO funcSetor : setorDTO.getFuncionarios()) {
                Funcionario func = new Funcionario();
                func.setId(UUID.fromString(funcSetor.getId()));
                func.setNome(funcSetor.getNome());
                func.setCpf(funcSetor.getCpf());
                func.setCargo(funcSetor.getCargo());
                func.setGenero(funcSetor.getGenero());
                func.setDataContratacao(funcSetor.getDataContratacao());
                func.setDataNascimento(funcSetor.getDataNascimento());
                func.setStatus(funcSetor.getStatus());

                list.add(func);
            }
        }

        return setor;
    }

    public Setor toSetorToSetorManipular(SetorManipularDTO dto){
        var setor = new Setor();
        setor.setId(UUID.fromString(dto.getId()));
        setor.setNome(dto.getNome());

        return setor;
    }

    public SetorManipularDTO toSetorManipularbyDto(SetorDTO dto){
        var setor = new SetorManipularDTO();
        setor.setId(dto.getId());
        setor.setNome(dto.getNome());

        return setor;
    }

    public SetorDTO toSetorDto(Setor setor){
        var setorDto = new SetorDTO();
        setorDto.setId(setor.getId().toString());
        setorDto.setNome(setor.getNome());

        return setorDto;
    }
}
