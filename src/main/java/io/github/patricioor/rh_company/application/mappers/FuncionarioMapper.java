package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioIdDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioManipularDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioSetorDTO;
import io.github.patricioor.rh_company.application.dto.Setor.SetorDTO;
import io.github.patricioor.rh_company.application.dto.Setor.SetorManipularDTO;
import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioMapper {

    public Funcionario toFuncionario(FuncionarioManipularDTO dto, String cpf){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setCpf(cpf);
        funcionario.setCargo(dto.getCargo());
        funcionario.setGenero(dto.getGenero());
        funcionario.setDataContratacao(dto.getDataContratacao());
        funcionario.setDataNascimento(dto.getDataNascimento());
        funcionario.setStatus(dto.getStatus());
        return funcionario;
    }
    public FuncionarioDTO toFuncionarioDTO (Funcionario funcionario){
        try {
            FuncionarioDTO dto = new FuncionarioDTO();
            dto.setId(funcionario.getId().toString());
            dto.setNome(funcionario.getNome());
            dto.setCpf(funcionario.getCpf());
            dto.setCargo(funcionario.getCargo());
            dto.setGenero(funcionario.getGenero());
            dto.setDataContratacao(funcionario.getDataContratacao());
            dto.setDataNascimento(funcionario.getDataNascimento());
            dto.setStatus(funcionario.getStatus());
            if (funcionario.getSetor() != null) {
                SetorManipularDTO setorManipularDTO = new SetorManipularDTO();
                setorManipularDTO.setId(funcionario.getSetor().getId().toString());
                setorManipularDTO.setNome(funcionario.getSetor().getNome());
                dto.setSetor(setorManipularDTO);
            }

            if (!funcionario.getFolhaPagamentos().isEmpty()){
                dto.setListaFolhaPagamento(funcionario.getFolhaPagamentos().stream().map(folhaPagamento -> folhaPagamento.getId().toString()).toList());
            }

            return dto;
        } catch ( ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }
    public FuncionarioIdDTO toFuncionarioIdDTO (Funcionario funcionario){
        try {
            FuncionarioIdDTO dto = new FuncionarioIdDTO();
            dto.setId(funcionario.getId().toString());
            dto.setNome(funcionario.getNome());
            dto.setCargo(funcionario.getCargo());
            dto.setGenero(funcionario.getGenero());
            dto.setDataContratacao(funcionario.getDataContratacao());
            dto.setDataNascimento(funcionario.getDataNascimento());
            dto.setStatus(funcionario.getStatus());
            if (funcionario.getSetor() != null) {
                SetorDTO setorDTO = new SetorDTO();
                setorDTO.setId(funcionario.getSetor().getId().toString());
                setorDTO.setNome(funcionario.getSetor().getNome());
                dto.setSetor(setorDTO);
            }

            return dto;
        } catch ( ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public FuncionarioDTO toFuncionarioDtoByFuncIdDto(String cpf,FuncionarioIdDTO funcionarioIdDTO){
        try {
            FuncionarioDTO dto = new FuncionarioDTO();
            dto.setId(funcionarioIdDTO.getId());
            dto.setNome(funcionarioIdDTO.getNome());
            dto.setCpf(cpf);
            dto.setCargo(funcionarioIdDTO.getCargo());
            dto.setGenero(funcionarioIdDTO.getGenero());
            dto.setDataContratacao(funcionarioIdDTO.getDataContratacao());
            dto.setDataNascimento(funcionarioIdDTO.getDataNascimento());
            dto.setStatus(funcionarioIdDTO.getStatus());
            if (funcionarioIdDTO.getSetor() != null) {
                SetorManipularDTO setorManipularDTO = new SetorManipularDTO();
                setorManipularDTO.setId(funcionarioIdDTO.getSetor().getId());
                setorManipularDTO.setNome(funcionarioIdDTO.getSetor().getNome());
                dto.setSetor(setorManipularDTO);
            }

            return dto;
        } catch ( ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }
    public FuncionarioSetorDTO toFuncionarioSetorDtoByFuncDTO(FuncionarioDTO funcionarioDTO){
        try {
            FuncionarioSetorDTO dto = new FuncionarioSetorDTO();
            dto.setId(funcionarioDTO.getId());
            dto.setNome(funcionarioDTO.getNome());
            dto.setCpf(funcionarioDTO.getCpf());
            dto.setCargo(funcionarioDTO.getCargo());
            dto.setGenero(funcionarioDTO.getGenero());
            dto.setDataContratacao(funcionarioDTO.getDataContratacao());
            dto.setDataNascimento(funcionarioDTO.getDataNascimento());
            dto.setStatus(funcionarioDTO.getStatus());


            return dto;
        } catch ( ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }


}
