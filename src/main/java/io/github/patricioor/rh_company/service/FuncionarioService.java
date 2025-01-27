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
        try{
            Funcionario funcionario = repository.findByCpf(cpf);
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
            Funcionario funcionario = repository.findFuncionarioByNome(nome);
            return funcionarioMapper.toFuncionarioDTO(funcionario);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    };

    public List<FuncionarioDTO> retornarListaFuncionariosPeloCargo(String cargo){
        try {
            List<Funcionario> lista = repository.findListFuncionariosByCargo(cargo);
            List<FuncionarioDTO> listaDto = new ArrayList<>();
            for(Funcionario funcionario: lista)
                listaDto.add(funcionarioMapper.toFuncionarioDTO(funcionario));
            return listaDto;
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<FuncionarioSetorDTO> retornarListaFuncionariosPeloSetorId(UUID id){
        try {
            List<Funcionario> lista = repository.findListFuncionariosBySetor(id);
            List<FuncionarioSetorDTO> listaDto = new ArrayList<>();
            for(Funcionario funcionario: lista)
                listaDto.add(funcionarioMapper.toFuncionarioSetorDtoByFuncDTO(funcionarioMapper.toFuncionarioDTO(funcionario)));
            return listaDto;
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<FuncionarioDTO> retornarListaFuncionariosPeloStatus(Boolean status){
        try {
            List<Funcionario> lista = repository.findListFuncionariosByStatus(status);
            List<FuncionarioDTO> listaDto = new ArrayList<>();
            for(Funcionario funcionario: lista)
                listaDto.add(funcionarioMapper.toFuncionarioDTO(funcionario));
            return listaDto;
        }   catch (ElementNotFoundException e) {
            throw new ElementNotFoundException("Funcionário");
        }
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
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Não foi possível fazer a alteração do setor");
        }
    }

    public void excluirFuncionarioDoSetor(UUID id){
        var func = repository.findById(id).orElseThrow(() -> new ElementNotFoundException("Funcionário"));
        func.setSetor(null);
        repository.updateFuncionarioByCpf(func);
    }

    public FuncionarioDTO excluirFuncionario(String cpf){
        var funcionario = repository.findByCpf(cpf);
        setorService.deletarFuncionarioDoSetor(funcionario.getId());
        repository.delete(funcionario);
        return funcionarioMapper.toFuncionarioDTO(funcionario);
    }
}