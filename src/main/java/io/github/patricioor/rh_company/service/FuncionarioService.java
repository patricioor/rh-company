package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioIdDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioManipularDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioSetorDTO;
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
        Funcionario funcionario = repository.findByCpf(cpf);
        if(funcionario == null){
            throw new ElementNotFoundException("Funcionário");
        }
        return funcionarioMapper.toFuncionarioIdDTO(funcionario);

    }

    public List<FuncionarioDTO> listarTodos(){
        List<Funcionario> list = repository.findAll();
        return list.stream().map(funcionarioMapper::toFuncionarioDTO).toList();
    }

    public FuncionarioDTO buscarPorNome (String nome){
        Funcionario funcionario = repository.findFuncionarioByNome(nome);
        if(funcionario == null){
            throw new ElementNotFoundException("Funcionário");
        }
        return funcionarioMapper.toFuncionarioDTO(funcionario);
    };

    public List<FuncionarioDTO> retornarListaFuncionariosPeloCargo(String cargo){
        List<Funcionario> lista = repository.findListFuncionariosByCargo(cargo);
        if(lista.isEmpty()){
            throw new ElementNotFoundException("Funcionário");
        }
        return lista.stream().map(funcionarioMapper::toFuncionarioDTO).toList();
    }

    public List<FuncionarioSetorDTO> retornarListaFuncionariosPeloSetorId(UUID id){
        List<Funcionario> lista = repository.findListFuncionariosBySetor(id);
        if(lista.isEmpty()){
            throw new ElementNotFoundException("Funcionário");
        }
        List<FuncionarioSetorDTO> listaDto = new ArrayList<>();
        for(Funcionario funcionario: lista)
            listaDto.add(funcionarioMapper.toFuncionarioSetorDtoByFuncDTO(funcionarioMapper.toFuncionarioDTO(funcionario)));
        return listaDto;

    }

    public List<FuncionarioDTO> retornarListaFuncionariosPeloStatus(Boolean status){
        List<Funcionario> lista = repository.findListFuncionariosByStatus(status);
        if(lista.isEmpty()){
            throw new ElementNotFoundException("Funcionário");
        }
        List<FuncionarioDTO> listaDto = new ArrayList<>();
        for(Funcionario funcionario: lista)
            listaDto.add(funcionarioMapper.toFuncionarioDTO(funcionario));
        return listaDto;
    }

    public FuncionarioDTO criarFuncionario( String cpf, FuncionarioManipularDTO funcionarioManipularDTO){
        if (repository.findByCpf(cpf) != null) {
            throw new ElementAlreadyExistsException("Funcionário");
        }

        Funcionario funcionario = funcionarioMapper.toFuncionario(funcionarioManipularDTO, cpf);

        repository.save(funcionario);

        return funcionarioMapper.toFuncionarioDTO(funcionario);
    }

    public FuncionarioDTO atualizarFuncionario(String cpf, FuncionarioManipularDTO funcionarioManipularDTO){
            repository.updateFuncionarioByCpf(funcionarioMapper.toFuncionario(funcionarioManipularDTO,cpf));

            var funcionarioIdDto = buscarPorCpf(cpf);
            if(funcionarioIdDto == null){
                throw new ElementNotFoundException("Funcionário");

            }

            return funcionarioMapper.toFuncionarioDtoByFuncIdDto(cpf, funcionarioIdDto);

    }

    public FuncionarioDTO alterarSetorFuncionar(String cpf, String nomeSetor){
        var funcionario = repository.findByCpf(cpf);

        if(funcionario == null) {
            throw new ElementNotFoundException("Funcionário");
        }
        if(funcionario.getSetor() != null){
            setorService.deletarFuncionarioDoSetor(funcionario.getSetor().getId());
        }

        var setor = setorMapper.toSetor(setorService.buscarPeloNome(nomeSetor));

        if(setor == null){
            throw new ElementNotFoundException("Setor");
        }

        setorService.inserirFuncionarioNoSetor(UUID.fromString(setor.getId().toString()), funcionario.getId());

        funcionario.setSetor(setor);
        repository.save(funcionario);

        return funcionarioMapper.toFuncionarioDTO(funcionario);
    }

    public void excluirFuncionarioDoSetor(UUID id){
        var func = repository.findById(id).orElseThrow(() -> new ElementNotFoundException("Funcionário"));
        func.setSetor(null);
        repository.updateFuncionarioByCpf(func);
    }

    public FuncionarioDTO excluirFuncionario(String cpf){
        var funcionario = repository.findByCpf(cpf);
        if(funcionario == null){
            throw new ElementNotFoundException("Funcionário");
        }
        setorService.deletarFuncionarioDoSetor(funcionario.getId());
        repository.delete(funcionario);
        return funcionarioMapper.toFuncionarioDTO(funcionario);
    }
}