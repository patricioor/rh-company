package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioIdDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioManipularDTO;
import io.github.patricioor.rh_company.application.mappers.FuncionarioMapper;
import io.github.patricioor.rh_company.application.mappers.SetorMapper;
import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.exception.ElementAlreadyExistsException;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService{
    private final IFuncionarioRepository repository;
    private final SetorService setorService;
    private final FuncionarioMapper funcionarioMapper;
    private final SetorMapper setorMapper;

    @Autowired
    public FuncionarioService(IFuncionarioRepository repository, SetorService setorService, FuncionarioMapper funcionarioMapper, SetorMapper setorMapper) {
        this.repository = repository;
        this.setorService = setorService;
        this.funcionarioMapper = funcionarioMapper;
        this.setorMapper = setorMapper;
    }

    public FuncionarioDTO buscarPorId (String id){
        return funcionarioMapper.toFuncionarioDTO(repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ElementNotFoundException("Funcionário")));
    }

    public FuncionarioIdDTO buscarPorCpf (String cpf){
        try{
            Funcionario funcionario = repository.findFuncionarioByCpf(cpf);
            return funcionarioMapper.toFuncionarioIdDTO(funcionario);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<FuncionarioDTO> listarTodos(){
        List<Funcionario> list = repository.findAll();
        List<FuncionarioDTO> listDto = new ArrayList<>();
        for(Funcionario f : list)
            listDto.add(funcionarioMapper.toFuncionarioDTO(f));

        return listDto;
    }

    public FuncionarioDTO buscarPorNome (String nome){
        try {
            Funcionario funcionario = repository.findFuncionarioByNome("%" + nome + "%");
            return funcionarioMapper.toFuncionarioDTO(funcionario);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    };

    public List<FuncionarioDTO> retornarFuncionariosPeloCargo(String cargo){
        try {
            List<Funcionario> lista = repository.findFuncionariosByCargo(cargo);
            List<FuncionarioDTO> listaDto = new ArrayList<>();
            for(Funcionario funcionario: lista)
                listaDto.add(funcionarioMapper.toFuncionarioDTO(funcionario));
            return listaDto;
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<FuncionarioDTO> retornarFuncionariosPeloSetorId(UUID id){
        try {
            List<Funcionario> lista = repository.findFuncionariosBySetor(id);
            List<FuncionarioDTO> listaDto = new ArrayList<>();
            for(Funcionario funcionario: lista)
                listaDto.add(funcionarioMapper.toFuncionarioDTO(funcionario));
            return listaDto;
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<FuncionarioDTO> retornarFuncionariosPeloStatus(Boolean status){
        try {
            List<Funcionario> lista = repository.findFuncionariosByStatus(status);
            List<FuncionarioDTO> listaDto = new ArrayList<>();
            for(Funcionario funcionario: lista)
                listaDto.add(funcionarioMapper.toFuncionarioDTO(funcionario));
            return listaDto;
        }   catch (ElementNotFoundException e) {
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public FuncionarioDTO criarFuncionario( String cpf, FuncionarioManipularDTO funcionarioManipularDTO){
        if (repository.findFuncionarioByCpf(cpf) != null) {
            throw new ElementAlreadyExistsException("Funcionário");
        }

        Funcionario funcionario = funcionarioMapper.toFuncionario(funcionarioManipularDTO, cpf);

        repository.save(funcionario);

        return funcionarioMapper.toFuncionarioDTO(funcionario);
    }

    public FuncionarioDTO atualizarFuncionario(String cpf, FuncionarioManipularDTO funcionarioManipularDTO){
        try {
            repository.updateFuncionarioByCpf(funcionarioMapper.toFuncionario(funcionarioManipularDTO,cpf));

            var funcionarioIdDto = buscarPorCpf(cpf);

            return funcionarioMapper.toFuncionarioDtoByFuncIdDto(cpf, funcionarioIdDto);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public FuncionarioDTO alterarSetorFuncionar(String cpf, String nomeSetor){
        try {
            var funcionario = repository.findFuncionarioByCpf(cpf);
            var setor = setorService.buscarSetorPeloNome(nomeSetor);

            if(setor != null){
                setorService.InserirFuncionarioNoSetor(UUID.fromString(setor.getId()), funcionario.getId());
            }

            funcionario.setSetor(setorMapper.toSetor(setor));
            repository.save(funcionario);

            return funcionarioMapper.toFuncionarioDTO(funcionario);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionario");
        }
    }

    public FuncionarioDTO excluirFuncionario(String cpf){
        var funcionario = repository.findFuncionarioByCpf(cpf);
        setorService.DeletarFuncionarioDoSetor(funcionario.getId());
        repository.delete(funcionario);
        return funcionarioMapper.toFuncionarioDTO(funcionario);
    }
}